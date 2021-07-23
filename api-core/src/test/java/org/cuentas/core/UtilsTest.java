package org.cuentas.core;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.cuentas.core.dto.CuentaDto;
import org.cuentas.core.dto.InteresDto;
import org.cuentas.core.dto.TypeAccount;

public class UtilsTest {

	public static List<CuentaDto> getCuentas() {
		List<CuentaDto> cuentas = new ArrayList<>();
		CuentaDto cuentaCorriente1 = new CuentaDto();
		cuentaCorriente1.setClient("Test");
		cuentaCorriente1.setType(TypeAccount.CORRIENTE);
		cuentaCorriente1.setAmount(320.0);
		cuentaCorriente1.setLimite(50.0);

		CuentaDto cuentaCorriente2 = new CuentaDto();
		cuentaCorriente2.setClient("Test");
		cuentaCorriente2.setType(TypeAccount.CORRIENTE);
		cuentaCorriente2.setAmount(320.0);
		cuentaCorriente2.setLimite(50.0);

		CuentaDto cuentaAhorro1 = new CuentaDto();
		cuentaAhorro1.setClient("Test");
		cuentaAhorro1.setType(TypeAccount.AHORRO);
		cuentaAhorro1.setAmount(320.0);

		CuentaDto cuentaAhorro2 = new CuentaDto();
		cuentaAhorro2.setClient("Test");
		cuentaAhorro2.setType(TypeAccount.AHORRO);
		cuentaAhorro2.setAmount(320.0);

		cuentas.add(cuentaCorriente1);
		cuentas.add(cuentaCorriente2);
		cuentas.add(cuentaAhorro1);
		cuentas.add(cuentaAhorro2);

		return cuentas;
	}

	public static List<InteresDto> getIntereses() {
		List<InteresDto> intereses = new ArrayList<>();
		InteresDto interes1 = new InteresDto();
		interes1.setDate(new Date(1627100000000l));
		interes1.setValor(2.66);

		InteresDto interes2 = new InteresDto();
		interes2.setDate(new Date(1626419097000l));
		interes2.setValor(3.22);

		intereses.add(interes1);
		intereses.add(interes2);

		return intereses;
	}

}
