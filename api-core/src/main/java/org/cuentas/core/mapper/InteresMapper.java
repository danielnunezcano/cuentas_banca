package org.cuentas.core.mapper;

import java.util.List;

import org.cuentas.core.dto.InteresDto;
import org.cuentas.model.entity.Interes;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface InteresMapper {

	InteresMapper INSTANCE = Mappers.getMapper(InteresMapper.class);

	public InteresDto interesToInteresDto(Interes request);

	public List<InteresDto> interesToInteresDtos(List<Interes> request);

	public Interes InteresDtoToInteres(InteresDto request);

}
