package org.springframework.samples.petclinic.model;

import java.util.List;
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
@Table(name="producto")
public class Producto extends NamedEntity {
	
	@NotNull
	private Integer cantidad;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="producto")
    private List<Oferta> ofertas;
	
}
