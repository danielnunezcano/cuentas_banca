package org.cuentas.core.mapper;

import java.util.List;

import org.cuentas.core.dto.CuentaDto;
import org.cuentas.core.dto.TypeAccount;
import org.cuentas.model.entity.Cuenta;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface CuentaMapper {

	CuentaMapper INSTANCE = Mappers.getMapper(CuentaMapper.class);

	default TypeAccount stringToTypeAccount(String type) {

		return TypeAccount.valueOf(type);

	}

	default String typeAccountToString(TypeAccount type) {
		return type.name();
	}

	public CuentaDto cuentaToCuentaDto(Cuenta request);

	public List<CuentaDto> cuentaToCuentaDtos(List<Cuenta> request);

	public Cuenta cuentaDtoToCuenta(CuentaDto request);

}
