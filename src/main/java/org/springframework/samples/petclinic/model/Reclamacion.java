package org.springframework.samples.petclinic.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "reclamacion")
public class Reclamacion extends BaseEntity {

	@Column(name="fecha")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@NotNull
	private LocalDate fecha;
	
	@Column(name = "descripcion")
	@NotEmpty
	private String descripcion;
	
	@ManyToOne
    @JoinColumn(name="cliente_id")
    private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name="servicio_id")
	private Servicio servicio;
	
	
}
