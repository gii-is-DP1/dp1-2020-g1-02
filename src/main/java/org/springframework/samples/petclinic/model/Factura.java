package org.springframework.samples.petclinic.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;


@Entity
@Data
@Table(name="factura")
public class Factura extends BaseEntity {

	
	@Column(name="fecha")
	@NotNull
	private LocalDate fecha;
	
	@Column(name="precio_total")
	@NotNull
	private Double precio_total;
	
	@ManyToOne
	@JoinColumn(name="proveedor_id")
	private Proveedor proveedor;
	
	@OneToOne(optional=false)
	@JoinColumn(name="pedido_id")
	private Pedido pedido;

}
