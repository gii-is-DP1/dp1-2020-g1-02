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

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Entity
@Data
@Table(name="contratoservicio")
public class ContratoServicio extends BaseEntity {
	
	@Column(name="fechainicial")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@NotNull
	private LocalDate fechainicial;
	
	@Column(name="fechafinal")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@NotNull
	private LocalDate fechafinal;
	
	@Column(name="fechapago")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@NotNull
	private LocalDate fechapago;
	
	@ManyToOne
	@JoinColumn(name="cliente_dni")
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name = "presupuesto_id")
	private Presupuesto presupuesto;
	
	@Column(name = "periodoprueba")
	@NotNull
	private Integer periodoPrueba;
	
	@OneToOne(optional=false)
	@JoinColumn(name="servicio_id")
	private Servicio servicio;

}
