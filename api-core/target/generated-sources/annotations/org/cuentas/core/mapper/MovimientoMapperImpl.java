package org.cuentas.core.mapper;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.cuentas.core.dto.CuentaDto;
import org.cuentas.core.dto.MovimientoDto;
import org.cuentas.model.entity.Cuenta;
import org.cuentas.model.entity.Movimiento;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-07-23T15:00:40+0200",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 1.8.0_291 (Oracle Corporation)"
)
public class MovimientoMapperImpl implements MovimientoMapper {

    @Override
    public MovimientoDto movimientoToMovimientoDto(Movimiento request) {
        if ( request == null ) {
            return null;
        }

        MovimientoDto movimientoDto = new MovimientoDto();

        movimientoDto.setId( request.getId() );
        movimientoDto.setOriginAccount( cuentaToCuentaDto( request.getOriginAccount() ) );
        movimientoDto.setDestinationAccount( cuentaToCuentaDto( request.getDestinationAccount() ) );
        movimientoDto.setValor( request.getValor() );
        movimientoDto.setTipo( request.getTipo() );
        if ( request.getDate() != null ) {
            movimientoDto.setDate( new Date( request.getDate().getTime() ) );
        }

        return movimientoDto;
    }

    @Override
    public List<MovimientoDto> movimientoToMovimientoDtos(List<Movimiento> request) {
        if ( request == null ) {
            return null;
        }

        List<MovimientoDto> list = new ArrayList<MovimientoDto>( request.size() );
        for ( Movimiento movimiento : request ) {
            list.add( movimientoToMovimientoDto( movimiento ) );
        }

        return list;
    }

    @Override
    public Movimiento movimientoDtoToMovimiento(MovimientoDto request) {
        if ( request == null ) {
            return null;
        }

        Movimiento movimiento = new Movimiento();

        movimiento.setId( request.getId() );
        movimiento.setOriginAccount( cuentaDtoToCuenta( request.getOriginAccount() ) );
        movimiento.setDestinationAccount( cuentaDtoToCuenta( request.getDestinationAccount() ) );
        movimiento.setValor( request.getValor() );
        movimiento.setTipo( request.getTipo() );
        movimiento.setDate( request.getDate() );

        return movimiento;
    }

    @Override
    public Movimiento cuentaDtoToMovimiento(CuentaDto request) {
        if ( request == null ) {
            return null;
        }

        Movimiento movimiento = new Movimiento();

        return movimiento;
    }

    @Override
    public Movimiento cuentaToMovimiento(Cuenta request) {
        if ( request == null ) {
            return null;
        }

        Movimiento movimiento = new Movimiento();

        return movimiento;
    }

    protected CuentaDto cuentaToCuentaDto(Cuenta cuenta) {
        if ( cuenta == null ) {
            return null;
        }

        CuentaDto cuentaDto = new CuentaDto();

        cuentaDto.setIdentifier( cuenta.getIdentifier() );
        cuentaDto.setClient( cuenta.getClient() );
        cuentaDto.setType( stringToTypeAccount( cuenta.getType() ) );
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
        cuenta.setType( typeAccountToString( cuentaDto.getType() ) );
        cuenta.setAmount( cuentaDto.getAmount() );
        cuenta.setLimite( cuentaDto.getLimite() );

        return cuenta;
    }
}
