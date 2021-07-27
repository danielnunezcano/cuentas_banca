package org.cuentas.core;

import java.util.ArrayList;
import java.util.List;

import org.cuentas.core.dto.Accion;
import org.cuentas.core.dto.CuentaDto;
import org.cuentas.core.dto.InteresDto;
import org.cuentas.core.dto.MovimientoDto;
import org.cuentas.core.dto.TransferenciaDto;
import org.cuentas.core.dto.TypeAccount;
import org.cuentas.core.service.CuentasService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class IntegrationTest {

	@Autowired
	private CuentasService cuentasService;

	List<CuentaDto> cuentas = new ArrayList<>();
	List<InteresDto> intereses = new ArrayList<>();

	@Before
	public void initTest() {

		cuentas = UtilsTest.getCuentas();
		intereses = UtilsTest.getIntereses();
	}
	
	@Test
	public void adeleteDataTest() {
		this.cuentasService.deleteInteres();
		this.cuentasService.deleteMovimientos();
		this.cuentasService.deleteCuenta();

	}

	@Test
	public void agregarCuentaTest() {
		for (CuentaDto cuenta : cuentas) {
			this.cuentasService.agregarCuenta(cuenta);
		}
		Long id1 = this.cuentasService.listarCuentas(TypeAccount.AHORRO).get(0).getIdentifier();
		for (InteresDto interes : intereses) {
			this.cuentasService.addInteres(id1, interes);
		}
	}

	@Test
	public void listarCuentasTest() {
		List<CuentaDto> lista = this.cuentasService.listarCuentasInteres(null);
		Assert.assertEquals(4, lista.size());
		Assert.assertEquals(intereses.get(0).getValor(), lista.get(2).getInteres());
	}

	@Test
	public void listarCuentasTypeTest() {
		Assert.assertEquals(2, this.cuentasService.listarCuentasInteres(TypeAccount.CORRIENTE).size());
		Assert.assertEquals(2, this.cuentasService.listarCuentasInteres(TypeAccount.AHORRO).size());
	}

	@Test
	public void retirarDineroTest() {
		Long cuenta = this.cuentasService.listarCuentas(TypeAccount.CORRIENTE).get(0).getIdentifier();
		Double amountInit = cuentasService.cuentasId(cuenta).getAmount();
		CuentaDto retirar = new CuentaDto();
		retirar.setIdentifier(cuenta);
		retirar.setAmount(25.0);
		this.cuentasService.retirarDinero(retirar);

		Assert.assertEquals((Double) (amountInit - retirar.getAmount()), cuentasService.cuentasId(cuenta).getAmount());
	}

	@Test
	public void depositarDineroTest() {
		Long cuenta = this.cuentasService.listarCuentas(TypeAccount.CORRIENTE).get(0).getIdentifier();
		Double amountInit = cuentasService.cuentasId(cuenta).getAmount();
		CuentaDto depositar = new CuentaDto();
		depositar.setIdentifier(cuenta);
		depositar.setAmount(32.0);
		this.cuentasService.depositarDinero(depositar);

		Assert.assertEquals((Double) (amountInit + depositar.getAmount()),
				cuentasService.cuentasId(cuenta).getAmount());
	}

	@Test
	public void transferenciaTest() {
		CuentaDto originAccount = this.cuentasService.listarCuentas(TypeAccount.CORRIENTE).get(0);
		CuentaDto destinationAccount = this.cuentasService.listarCuentas(TypeAccount.CORRIENTE).get(1);
		Double amountInitCuenta1 = cuentasService.cuentasId(originAccount.getIdentifier()).getAmount();
		Double amountInitCuenta2 = cuentasService.cuentasId(destinationAccount.getIdentifier()).getAmount();
		TransferenciaDto transferencia = new TransferenciaDto();
		transferencia.setOriginAccount(originAccount.getIdentifier());
		transferencia.setDestinationAccount(destinationAccount.getIdentifier());
		transferencia.setAmount(30.0);
		this.cuentasService.transferencia(transferencia);

		Assert.assertEquals((Double) (amountInitCuenta1 - transferencia.getAmount()),
				cuentasService.cuentasId(originAccount.getIdentifier()).getAmount());
		Assert.assertEquals((Double) (amountInitCuenta2 + transferencia.getAmount()),
				cuentasService.cuentasId(destinationAccount.getIdentifier()).getAmount());

	}

	@Test
	public void tz_revertirMovimiento() {
		List<CuentaDto> cuentas = this.cuentasService.listarCuentas(null);
		Long idCuenta = cuentas.get(0).getIdentifier();
		MovimientoDto movimiento = this.cuentasService.getMovimientos(idCuenta).get(0);

		CuentaDto originAccountBefore = this.cuentasService.cuentasId(movimiento.getOriginAccount().getIdentifier());

		this.cuentasService.revertirMovimiento(movimiento.getId());

		CuentaDto originAccountAfter = this.cuentasService.cuentasId(movimiento.getOriginAccount().getIdentifier());

		if(movimiento.getTipo().equals(Accion.DEPOSITO.name())) {
			Assert.assertEquals((Double) (originAccountBefore.getAmount() - movimiento.getValor()),
					originAccountAfter.getAmount());
		} else {
			Assert.assertEquals((Double) (originAccountBefore.getAmount() + movimiento.getValor()),
					originAccountAfter.getAmount());
		}
		

	}
	
	@Test
	public void tz_revertirTransferencia() {
		List<CuentaDto> cuentas = this.cuentasService.listarCuentas(null);
		Long idCuenta = cuentas.get(0).getIdentifier();
		MovimientoDto movimiento = this.cuentasService.getMovimientos(idCuenta).get(2);

		CuentaDto originAccountBefore = this.cuentasService.cuentasId(movimiento.getOriginAccount().getIdentifier());
		CuentaDto destinationAccountBefore = this.cuentasService
				.cuentasId(movimiento.getDestinationAccount().getIdentifier());

		this.cuentasService.revertirMovimiento(movimiento.getId());

		CuentaDto originAccountAfter = this.cuentasService.cuentasId(movimiento.getOriginAccount().getIdentifier());
		CuentaDto destinationAccountAfter = this.cuentasService
				.cuentasId(movimiento.getDestinationAccount().getIdentifier());

		Assert.assertEquals((Double) (originAccountBefore.getAmount() + movimiento.getValor()),
				originAccountAfter.getAmount());
		Assert.assertEquals((Double) (destinationAccountBefore.getAmount() - movimiento.getValor()),
				destinationAccountAfter.getAmount());

	}

	@Test
	public void zdeleteDataTest() {
		adeleteDataTest();

	}

}
