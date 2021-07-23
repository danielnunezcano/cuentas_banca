package org.cuentas.core.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.cuentas.core.dto.CuentaDto;
import org.cuentas.core.dto.InteresDto;
import org.cuentas.core.dto.TypeAccount;
import org.cuentas.model.entity.Cuenta;
import org.cuentas.model.entity.Interes;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-07-23T15:00:40+0200",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 1.8.0_291 (Oracle Corporation)"
)
public class InteresMapperImpl implements InteresMapper {

    @Override
    public InteresDto interesToInteresDto(Interes request) {
        if ( request == null ) {
            return null;
        }

        InteresDto interesDto = new InteresDto();

        interesDto.setId( request.getId() );
        interesDto.setValor( request.getValor() );
        interesDto.setDate( request.getDate() );
        interesDto.setCuenta( cuentaToCuentaDto( request.getCuenta() ) );

        return interesDto;
    }

    @Override
    public List<InteresDto> interesToInteresDtos(List<Interes> request) {
        if ( request == null ) {
            return null;
        }

        List<InteresDto> list = new ArrayList<InteresDto>( request.size() );
        for ( Interes interes : request ) {
            list.add( interesToInteresDto( interes ) );
        }

        return list;
    }

    @Override
    public Interes InteresDtoToInteres(InteresDto request) {
        if ( request == null ) {
            return null;
        }

        Interes interes = new Interes();

        interes.setId( request.getId() );
        interes.setValor( request.getValor() );
        interes.setDate( request.getDate() );
        interes.setCuenta( cuentaDtoToCuenta( request.getCuenta() ) );

        return interes;
    }

    protected CuentaDto cuentaToCuentaDto(Cuenta cuenta) {
        if ( cuenta == null ) {
            return null;
        }

        CuentaDto cuentaDto = new CuentaDto();

        cuentaDto.setIdentifier( cuenta.getIdentifier() );
        cuentaDto.setClient( cuenta.getClient() );
        if ( cuenta.getType() != null ) {
            cuentaDto.setType( Enum.valueOf( TypeAccount.class, cuenta.getType() ) );
        }
        cuentaDto.setAmount( cuenta.getAmount() );
        cuentaDto.setLimite( cuenta.getLimite() );

        return cuentaDto;
    }

    protected Cuenta cuentaDtoToCuenta(CuentaDto cuentaDto) {
        if ( cuentaDto == null ) {
            return null;
        }

        Cuenta cuenta = new Cuenta();

        cuenta.setIdentifier( cuentaDto.getIdentifier() );
        cuenta.setClient( cuentaDto.getClient() );
        if ( cuentaDto.getType() != null ) {
            cuenta.setType( cuentaDto.getType().name() );
        }
        cuenta.setAmount( cuentaDto.getAmount() );
        cuenta.setLimite( cuentaDto.getLimite() );

        return cuenta;
    }
}
