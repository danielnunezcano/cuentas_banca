package org.cuentas.core.controller;

import java.util.List;

import org.cuentas.core.dto.CuentaDto;
import org.cuentas.core.dto.MovimientoDto;
import org.cuentas.core.dto.TransferenciaDto;
import org.cuentas.core.dto.TypeAccount;
import org.cuentas.core.service.CuentasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/cuentas")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CuentasController {

	private final CuentasService cuentasService;

	@PostMapping
	public void agregarCuenta(@RequestBody CuentaDto cuentaDto) {
		this.cuentasService.agregarCuenta(cuentaDto);
	}

	@GetMapping
	public List<CuentaDto> listarCuentas() {
		return this.cuentasService.listarCuentasInteres(null);
	}

	@GetMapping(value = "/{type}")
	public List<CuentaDto> listarCuentasType(@PathVariable TypeAccount type) {
		return this.cuentasService.listarCuentasInteres(type);
	}

	@PostMapping(value = "/retirarDinero")
	public void retirarDinero(@RequestBody CuentaDto input) {
		this.cuentasService.retirarDinero(input);
	}

	@PostMapping(value = "/depositarDinero")
	public void depositarDinero(@RequestBody CuentaDto input) {
		this.cuentasService.depositarDinero(input);
	}

	@PostMapping(value = "/transferencia")
	public void transferencia(@RequestBody TransferenciaDto transferencia) {
		this.cuentasService.transferencia(transferencia);
	}
	
	@GetMapping(value = "/movimientos/{cuenta}")
	public List<MovimientoDto> getMovimientos(@PathVariable Long cuenta) {
		return this.cuentasService.getMovimientos(cuenta);
	}
	
	@GetMapping(value = "/movimientos/revertirmovimiento/{movimiento}")
	public void revertirMovimiento(@PathVariable Long movimiento) {
		this.cuentasService.revertirMovimiento(movimiento);
	}

}
