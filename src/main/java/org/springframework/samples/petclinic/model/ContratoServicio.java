package org.springframework.samples.petclinic.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="contratoservicio")
public class ContratoServicio extends Contrato {
	
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate fechapago;
	
	@Column(name="periodoprueba")
	@NotNull
	private Boolean periodoPrueba;
	
	@OneToOne(optional=true, cascade=CascadeType.ALL)
	@JoinColumn(name="presupuesto")
	private Presupuesto presupuesto;

}
