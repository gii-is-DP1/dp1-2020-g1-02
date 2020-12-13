package org.springframework.samples.petclinic.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;


@Entity
@Data
@Table(name="facturas")
public class Factura extends BaseEntity {

	
	@Column(name="fecha")
	@NotNull
	private LocalDate fecha;
	
	@Column(name="precio_total")
	@NotNull
	private Double precio_total;
	
	@Column(name="id_prov")
	@NotNull
	private Integer id_prov;
	
	@Column(name="id_ped")
	@NotNull
	private Integer id_ped;

}
