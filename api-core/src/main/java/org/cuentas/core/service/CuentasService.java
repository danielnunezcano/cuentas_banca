package org.cuentas.core.service;

import java.util.List;

import org.cuentas.core.dto.CuentaDto;
import org.cuentas.core.dto.InteresDto;
import org.cuentas.core.dto.MovimientoDto;
import org.cuentas.core.dto.TransferenciaDto;
import org.cuentas.core.dto.TypeAccount;

public interface CuentasService {
	public void agregarCuenta(CuentaDto cuentaDto);
	public void deleteCuenta();
	public CuentaDto cuentasId(Long id);
	public List<CuentaDto> listarCuentas(TypeAccount type);
	public List<CuentaDto> listarCuentasInteres(TypeAccount type);
	public void retirarDinero(CuentaDto cuentaDto);
	public void depositarDinero(CuentaDto cuentaDto);
	public void transferencia(TransferenciaDto transferencia);
	public List<MovimientoDto> getMovimientos(Long idCuenta);
	public MovimientoDto getMovimiento(Long idMovimiento);
	public void revertirMovimiento(Long idMovimiento);
	public void addInteres(Long cuentaId, InteresDto interesDto);
	public void deleteInteres();
	public void deleteMovimientos();
	
}
