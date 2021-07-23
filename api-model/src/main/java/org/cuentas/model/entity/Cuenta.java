package org.cuentas.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cuentas", schema = "cuentas")
public class Cuenta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "identifier")
	private Long identifier;
	@Column(name = "client")
	private String client;
	@Column(name = "type")
	private String type;
	@Column(name = "amount")
	private Double amount;
	@Column(name = "limite")
	private Double limite;
}
