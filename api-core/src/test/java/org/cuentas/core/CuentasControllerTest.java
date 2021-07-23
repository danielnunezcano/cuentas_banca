package org.cuentas.core;

import static org.mockito.Mockito.doNothing;

import java.util.ArrayList;
import java.util.List;

import org.cuentas.core.controller.CuentasController;
import org.cuentas.core.dto.CuentaDto;
import org.cuentas.core.dto.MovimientoDto;
import org.cuentas.core.dto.TransferenciaDto;
import org.cuentas.core.dto.TypeAccount;
import org.cuentas.core.service.CuentasService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CuentasControllerTest {

	@InjectMocks
	private CuentasController cuentasController;

	@Mock
	CuentasService cuentasService;

	@Before
	public void initTests() {
		doNothing().when(cuentasService).retirarDinero(Mockito.any());
		doNothing().when(cuentasService).depositarDinero(Mockito.any());
		doNothing().when(cuentasService).transferencia(Mockito.any());
		List<MovimientoDto> movimientos = new ArrayList<MovimientoDto>();
		Mockito.when(this.cuentasService.getMovimientos(Mockito.anyLong())).thenReturn(movimientos);
		doNothing().when(cuentasService).revertirMovimiento(Mockito.any());
	}

	@Test
	public void agregarCuentaTest() {
		doNothing().when(cuentasService).agregarCuenta(Mockito.any());
		this.cuentasService.agregarCuenta(new CuentaDto());
	}

	@Test
	public void listarCuentasTest() {
		Assert.assertEquals(cuentasController.listarCuentas().size(), 0);

	}

	@Test
	public void listarCuentasTypeTest() {
		Assert.assertEquals(cuentasController.listarCuentasType(TypeAccount.AHORRO).size(), 0);
	}

	@Test
	public void retirarDineroTest() {

		cuentasController.retirarDinero(new CuentaDto());
	}

	@Test
	public void depositarDineroTest() {

		cuentasController.depositarDinero(new CuentaDto());
	}

	@Test
	public void transferenciaTest() {

		cuentasController.transferencia(new TransferenciaDto());
	}

	@Test
	public void getMovimientosTest() {

		cuentasController.getMovimientos(0l);
	}

	@Test
	public void revertirMovimientoTest() {

		cuentasController.revertirMovimiento(null);
	}

}
