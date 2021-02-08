package org.springframework.samples.petclinic.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Data;


@Entity
@Data
@Table(name="factura")
public class Factura extends BaseEntity {

	@NotNull
	private LocalDate fecha;
	
	@Min(0)
	@NotNull
	private Double precio_total;
	
	@ManyToOne
	@JoinColumn(name="proveedor")
	private Proveedor proveedor;
	
	@OneToOne(optional=false)
	@JoinColumn(name="pedido")
	private Pedido pedido;

}
