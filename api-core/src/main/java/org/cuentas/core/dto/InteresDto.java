package org.cuentas.core.dto;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "interes", schema = "cuentas")
public class InteresDto {
	@JsonProperty("id")
	private Long id;
	@JsonProperty("valor")
	private Double valor;
	@JsonProperty("date")
	private Date date;
	@JsonProperty("cuenta")
	private CuentaDto cuenta;
}