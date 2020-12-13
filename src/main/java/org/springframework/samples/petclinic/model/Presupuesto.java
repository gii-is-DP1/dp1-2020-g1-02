package org.springframework.samples.petclinic.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "presupuesto")
public class Presupuesto extends BaseEntity {
	
	@Column(name="precio")
	@NotEmpty
	private Double precio;
	
	@Column(name="tipopresupuesto")
	@NotNull
	private TipoPresupuesto tipopresupuesto;
	
}
