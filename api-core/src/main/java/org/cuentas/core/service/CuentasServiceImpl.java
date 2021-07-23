package org.cuentas.core.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.cuentas.core.dto.Accion;
import org.cuentas.core.dto.CuentaDto;
import org.cuentas.core.dto.InteresDto;
import org.cuentas.core.dto.MovimientoDto;
import org.cuentas.core.dto.TransferenciaDto;
import org.cuentas.core.dto.TypeAccount;
import org.cuentas.core.expection.CustomException;
import org.cuentas.core.mapper.CuentaMapper;
import org.cuentas.core.mapper.InteresMapper;
import org.cuentas.core.mapper.MovimientoMapper;
import org.cuentas.data.repository.CuentasRepository;
import org.cuentas.data.repository.InteresRepository;
import org.cuentas.data.repository.MovimientosRepository;
import org.cuentas.model.entity.Cuenta;
import org.cuentas.model.entity.Interes;
import org.cuentas.model.entity.Movimiento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CuentasServiceImpl implements CuentasService {

	private final CuentasRepository cuentasRepository;
	private final MovimientosRepository movimientosRepository;
	private final InteresRepository interesRepository;

	@Override
	public void agregarCuenta(CuentaDto cuentaDto) {
		Cuenta cuenta = CuentaMapper.INSTANCE.cuentaDtoToCuenta(cuentaDto);
		this.cuentasRepository.save(cuenta);

	}
	
	@Override
	public List<CuentaDto> listarCuentas(TypeAccount type) {
		List<CuentaDto> listCuentas = new ArrayList<>();
		if (type != null) {
			if (type.equals(TypeAccount.AHORRO)) {
				listCuentas = CuentaMapper.INSTANCE.cuentaToCuentaDtos(this.cuentasRepository.findByType(type.name()));

			} else {
				listCuentas = CuentaMapper.INSTANCE.cuentaToCuentaDtos(this.cuentasRepository.findByType(type.name()));
			}

		} else {
			List<Cuenta> cuentas = this.cuentasRepository.findAll();
			listCuentas = CuentaMapper.INSTANCE.cuentaToCuentaDtos(cuentas);
		}

		 return listCuentas;
	}
	
	@Override
	public CuentaDto cuentasId(Long id) {
		Cuenta cuenta = this.cuentasRepository.findById(id).get();
		return CuentaMapper.INSTANCE.cuentaToCuentaDto(cuenta);
		
	}
	
	@Override
	public List<CuentaDto> listarCuentasInteres(TypeAccount type) {
		return listarCuentas(type).stream().map(p ->
		 	addInteresCuenta(p)).collect(Collectors.toList());

	}

	private CuentaDto addInteresCuenta(CuentaDto cuentaDto) {
		if (cuentaDto.getType().equals(TypeAccount.AHORRO)) {
			List<Interes> interes = 
					interesRepository
						.findByCuentaOrderByDateDesc(CuentaMapper.INSTANCE
									.cuentaDtoToCuenta(cuentaDto));
			if(!interes.isEmpty()) {
				cuentaDto.setInteres(interes.get(0).getValor());
			}
			
		}
		return cuentaDto;
	}

	@Override
	public void retirarDinero(CuentaDto cuentaDto) {
		Optional<Cuenta> accounts = cuentasRepository.findById(cuentaDto.getIdentifier());
		if (accounts.isPresent()) {
			if (TypeAccount.CORRIENTE.name().equals(accounts.get().getType())
					&& 
					(
						(accounts.get().getLimite() != null && accounts.get().getLimite() >= cuentaDto.getAmount())
						|| accounts.get().getLimite() == null
					)
					&& accounts.get().getAmount() >= cuentaDto.getAmount()) {
				retirarODepositar(accounts, cuentaDto.getAmount(), Accion.RETIRADA);
				addMovimiento(cuentaDto,Accion.RETIRADA);
			} else if (TypeAccount.AHORRO.name().equals(accounts.get().getType())
					&& accounts.get().getAmount() >= cuentaDto.getAmount()) {
				retirarODepositar(accounts, cuentaDto.getAmount(), Accion.RETIRADA);
				addMovimiento(cuentaDto,Accion.RETIRADA);
			} else {
				throw new CustomException("Se ha superado el limite o no hay suficiente dinero");
			}
		} else {
			throw new CustomException("No se encuentra la cuenta");
		}

	}

	@Override
	public void depositarDinero(CuentaDto cuentaDto) {
		Optional<Cuenta> accounts = cuentasRepository.findById(cuentaDto.getIdentifier());
		if (accounts.isPresent()) {
			retirarODepositar(accounts, cuentaDto.getAmount(), Accion.DEPOSITO);
			addMovimiento(cuentaDto,Accion.DEPOSITO);
		} else {
			throw new CustomException("No se encuentra la cuenta");
		}
	}

	private void retirarODepositar(Optional<Cuenta> account, Double amount, Accion accion) {

		if (account.isPresent()) {
			Cuenta cuenta = account.get();
			if (Accion.RETIRADA.equals(accion)) {
				cuenta.setAmount(account.get().getAmount() - amount);
			} else {
				cuenta.setAmount(amount + account.get().getAmount());
			}
			cuentasRepository.save(cuenta);
		} else {
			throw new CustomException("No se encuentra la cuenta");
		}

	}

	@Override
	public void transferencia(TransferenciaDto transferencia) {
		Optional<Cuenta> originAccount = cuentasRepository.findById(transferencia.getOriginAccount());
		Optional<Cuenta> destinationAccount = cuentasRepository.findById(transferencia.getDestinationAccount());

		if (TypeAccount.CORRIENTE.name().equalsIgnoreCase(originAccount.get().getType())
				&& originAccount.get().getLimite() >= transferencia.getAmount()
				&& TypeAccount.CORRIENTE.name().equalsIgnoreCase(destinationAccount.get().getType())) {
			retirarODepositar(originAccount, transferencia.getAmount(), Accion.RETIRADA);
			addMovimientoTransferencia(originAccount.get(),destinationAccount.get(),transferencia.getAmount());
			retirarODepositar(destinationAccount, transferencia.getAmount(), Accion.DEPOSITO);
			addMovimientoTransferencia(destinationAccount.get(),originAccount.get(),-(transferencia.getAmount()));
		} else {
			throw new CustomException("Se ha superado el limite o no hay suficiente dinero");
		}
	}

	@Override
	public List<MovimientoDto> getMovimientos(Long idCuenta) {
		Optional<Cuenta> account = cuentasRepository.findById(idCuenta);
		if (account.isPresent()) {
			return MovimientoMapper.INSTANCE
					.movimientoToMovimientoDtos(movimientosRepository.findByOriginAccount(account.get()));
		} else {
			throw new CustomException("No se encuentra la cuenta");
		}

	}
	
	@Override
	public MovimientoDto getMovimiento(Long idMovimiento) {
		Optional<Movimiento> movimiento = movimientosRepository.findById(idMovimiento);
		if (movimiento.isPresent()) {
			return MovimientoMapper.INSTANCE
					.movimientoToMovimientoDto(movimiento.get());
		} else {
			throw new CustomException("No se encuentra la cuenta");
		}

	}

	private void addMovimiento(CuentaDto cuentaDto, Accion accion) {

		Movimiento movimiento = new Movimiento();
		Cuenta cuenta = cuentasRepository.findById(cuentaDto.getIdentifier()).get();
		movimiento.setOriginAccount(cuenta);
		movimiento.setDate(new Date());
		movimiento.setValor(
				accion.name().equals(Accion.DEPOSITO.name()) ? cuentaDto.getAmount() : -(cuentaDto.getAmount()));
		movimiento.setTipo(accion.name());
		movimientosRepository.save(movimiento);

	}

	private void addMovimientoTransferencia(Cuenta origin, Cuenta destination, Double amount) {

		Movimiento movimiento = new Movimiento();
		movimiento.setOriginAccount(origin);
		movimiento.setDestinationAccount(destination);
		movimiento.setDate(new Date());
		movimiento.setTipo(Accion.TRANSFERENCIA.name());
		movimiento.setValor(amount);
		movimientosRepository.save(movimiento);

	}
	
	public void revertirMovimiento(Long idMovimiento) {
		MovimientoDto movimiento = getMovimiento(idMovimiento);
		
		if(movimiento.getTipo().equals(Accion.TRANSFERENCIA.name())) {
			revertirTransferencia(movimiento);
		} else {
			revertirMovimientoSimple(movimiento);
		}
		
		
	}
	
	private void revertirTransferencia(MovimientoDto movimiento) {
		CuentaDto originAccount = movimiento.getOriginAccount();
		CuentaDto destinationAccount = movimiento.getDestinationAccount();
		
		TransferenciaDto transferencia = new TransferenciaDto();
		
		transferencia.setAmount(movimiento.getValor());
		transferencia.setOriginAccount(destinationAccount.getIdentifier());
		transferencia.setDestinationAccount(originAccount.getIdentifier());
		
		transferencia(transferencia);
	}
	
	private void revertirMovimientoSimple(MovimientoDto movimiento) {
		
		CuentaDto movimientoSimple = new CuentaDto();
		movimientoSimple.setAmount(movimiento.getValor());
		movimientoSimple.setIdentifier(movimiento.getOriginAccount().getIdentifier());
		
		if(movimiento.getTipo().equals(Accion.DEPOSITO.name())) {
			retirarDinero(movimientoSimple);
		} else {
			depositarDinero(movimientoSimple);
		}
		
	}

	public void deleteCuenta() {
		cuentasRepository.deleteByTest();
	}

	public void addInteres(Long cuentaId, InteresDto interesDto) {
		Interes interes = InteresMapper.INSTANCE.InteresDtoToInteres(interesDto);
		Cuenta cuenta = cuentasRepository.findById(cuentaId).get();
		interes.setCuenta(cuenta);
		interesRepository.save(interes);
	}
	
	public void deleteInteres() {
		interesRepository.deleteByTest();
	}
	
	public void deleteMovimientos() {
		movimientosRepository.deleteByTest();
	}


}
