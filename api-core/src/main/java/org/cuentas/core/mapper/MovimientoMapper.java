package org.cuentas.core.mapper;

import java.util.List;

import org.cuentas.core.dto.CuentaDto;
import org.cuentas.core.dto.MovimientoDto;
import org.cuentas.core.dto.TypeAccount;
import org.cuentas.model.entity.Cuenta;
import org.cuentas.model.entity.Movimiento;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface MovimientoMapper {

	MovimientoMapper INSTANCE = Mappers.getMapper(MovimientoMapper.class);

	default TypeAccount stringToTypeAccount(String type) {

		return TypeAccount.valueOf(type);

	}

	default String typeAccountToString(TypeAccount type) {
		return type.name();
	}

	public MovimientoDto movimientoToMovimientoDto(Movimiento request);
	
	public List<MovimientoDto> movimientoToMovimientoDtos(List<Movimiento> request);
	
	public Movimiento movimientoDtoToMovimiento(MovimientoDto request);
	
	public Movimiento cuentaDtoToMovimiento(CuentaDto request);
	
	public Movimiento cuentaToMovimiento(Cuenta request);
	
	

}
