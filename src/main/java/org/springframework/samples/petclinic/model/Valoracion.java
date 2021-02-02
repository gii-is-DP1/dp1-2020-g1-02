package org.springframework.samples.petclinic.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "valoracion")
public class Valoracion extends BaseEntity {
	
	@Column(name="fecha")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@NotNull
	private LocalDate fecha;
	
	
	@Column(name = "nivelsatisfaccion")
	@NotNull
	private NivelSatisfaccion nivelsatisfaccion;
	
	@ManyToOne
    @JoinColumn(name="cliente_id")
    private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name="servicio_id")
	private Servicio servicio;

}
