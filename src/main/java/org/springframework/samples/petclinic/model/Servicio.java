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
@Table(name = "servicio")
public class Servicio extends BaseEntity {
	
	@Column(name = "lugar")
	@NotEmpty
	private String lugar;
	
	@Column(name = "tipocategoria")
	@NotNull
	private TipoCategoria tipocategoria;
	
	@Column(name = "fechainicio")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@NotEmpty
	private LocalDate fechainicio;
	
	@Column(name = "fechafin")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@NotEmpty
	private LocalDate fechafin;
	
	@Column(name= "estado")
	@NotNull
	private EstadoServicio estado;
	
}