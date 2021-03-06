package org.cuentas.core.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class TransferenciaDto {

	@JsonProperty("originAccount")
	private Long originAccount;
	@JsonProperty("destinationAccount")
	private Long destinationAccount;
	@JsonProperty("amount")
	private Double amount;
	
}
