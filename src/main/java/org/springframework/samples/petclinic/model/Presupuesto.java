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
	
	
	@Column(name="precio")
	@NotNull
	private Double precio;
	
	@Column(name="tipopresupuesto")
	@NotNull
	private TipoPresupuesto tipopresupuesto;
	
	@Column(name="estado")
	@NotNull
	private EstadoServicio estado;
	
	@ManyToOne
	@JoinColumn(name="servicio_id")
	private Servicio servicio;
}
