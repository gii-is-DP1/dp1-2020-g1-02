package org.springframework.samples.petclinic.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Data
@Table(name="productos")
public class Producto extends NamedEntity {
	
	@Column(name="cantidad")
	@NotNull
	private Integer cantidadStock;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="producto")
    private Set<Oferta> ofertas;
	
}
