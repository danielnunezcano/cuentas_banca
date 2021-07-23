package org.cuentas.model.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "movimientos", schema = "cuentas")
public class Movimiento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@ManyToOne
    @JoinColumn(name="originAccount")
	private Cuenta originAccount;
	@ManyToOne
    @JoinColumn(name="destinationAccount")
	private Cuenta destinationAccount;
	@Column(name = "valor")
	private Double valor;
	@Column(name = "tipo")
	private String tipo;
	@Column(name = "date")
	private Date date;
}