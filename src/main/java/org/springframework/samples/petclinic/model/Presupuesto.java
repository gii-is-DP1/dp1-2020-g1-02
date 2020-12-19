package org.springframework.samples.petclinic.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "presupuesto")
public class Presupuesto extends BaseEntity {
	
	@Column(name="precio")
	@NotNull
	private Double precio;
	
	@Column(name="tipopresupuesto")
	@NotNull
	private TipoPresupuesto tipopresupuesto;
	
	@ManyToOne
	@JoinColumn(name="instalacion_id")
	private Instalacion instalacion;
}
