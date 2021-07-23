package org.cuentas.core.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.cuentas.core.dto.CuentaDto;
import org.cuentas.model.entity.Cuenta;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-07-23T15:00:40+0200",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 1.8.0_291 (Oracle Corporation)"
)
public class CuentaMapperImpl implements CuentaMapper {

    @Override
    public CuentaDto cuentaToCuentaDto(Cuenta request) {
        if ( request == null ) {
            return null;
        }

        CuentaDto cuentaDto = new CuentaDto();

        cuentaDto.setIdentifier( request.getIdentifier() );
        cuentaDto.setClient( request.getClient() );
        cuentaDto.setType( stringToTypeAccount( request.getType() ) );
        cuentaDto.setAmount( request.getAmount() );
        cuentaDto.setLimite( request.getLimite() );

        return cuentaDto;
    }

    @Override
    public List<CuentaDto> cuentaToCuentaDtos(List<Cuenta> request) {
        if ( request == null ) {
            return null;
        }

        List<CuentaDto> list = new ArrayList<CuentaDto>( request.size() );
        for ( Cuenta cuenta : request ) {
            list.add( cuentaToCuentaDto( cuenta ) );
        }

        return list;
    }

    @Override
    public Cuenta cuentaDtoToCuenta(CuentaDto request) {
        if ( request == null ) {
            return null;
        }

        Cuenta cuenta = new Cuenta();

        cuenta.setIdentifier( request.getIdentifier() );
        cuenta.setClient( request.getClient() );
        cuenta.setType( typeAccountToString( request.getType() ) );
        cuenta.setAmount( request.getAmount() );
        cuenta.setLimite( request.getLimite() );

        return cuenta;
    }
}
