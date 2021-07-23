package org.cuentas.core.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class CuentaDto {

	@JsonProperty("identifier")
	private Long identifier;
	@JsonProperty("client")
	private String client;
	@JsonProperty("type")
	private TypeAccount type;
	@JsonProperty("amount")
	private Double amount;
	@JsonProperty("interes")
	private Double interes;
	@JsonProperty("limite")
	private Double limite;
	
}
