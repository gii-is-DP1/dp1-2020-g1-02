package org.springframework.samples.petclinic.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Data;


@Entity
@Data
@Table(name="factura")
public class Factura extends BaseEntity {
	
	@Column(name="fecha")
	@NotEmpty
	private LocalDate fecha;
	
	@Column(name="precio_total")
	@NotEmpty
	private Double precio_total;

}
