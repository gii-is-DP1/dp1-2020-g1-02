package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Entity
@Data
@Table(name="ofertas")
public class Oferta extends NamedEntity {
	
	@Column(name="precioU")
	@NotEmpty
	private String precioU;
	
	@ManyToOne
    @JoinColumn(name="producto_id")
    private Producto producto;
	
}