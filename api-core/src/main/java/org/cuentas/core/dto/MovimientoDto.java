package org.cuentas.core.dto;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovimientoDto {

	@JsonProperty("id")
	private Long id;
	@JsonProperty("originAccount")
	private CuentaDto originAccount;
	@JsonProperty("destinationAccount")
	private CuentaDto destinationAccount;
	@JsonProperty("valor")
	private Double valor;
	@JsonProperty("tipo")
	private String tipo;
	@JsonProperty("date")
	private Date date;
}