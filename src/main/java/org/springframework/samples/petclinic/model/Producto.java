package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Entity
@Data
@Table(name="producto")
public class Producto extends BaseEntity {
	
	@Column(name="name")
	@NotEmpty
	private String name;
	
	@Column(name="precio")
	@NotEmpty
	private Double precio;
	
}
