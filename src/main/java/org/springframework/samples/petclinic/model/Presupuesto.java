package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "presupuesto")
public class Presupuesto extends BaseEntity {
	
	
	@NotNull
	private Double precio;
	
	@NotNull
	private TipoPresupuesto tipopresupuesto;
	
	@NotNull
	private EstadoServicio estado;
	
	@ManyToOne
	@JoinColumn(name="servicio_id")
	private Servicio servicio;
}
