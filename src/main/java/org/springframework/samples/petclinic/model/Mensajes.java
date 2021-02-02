package org.springframework.samples.petclinic.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Entity
@Data
@Table(name="mensajesAdmin")
public class Mensajes extends BaseEntity{
	
	
	@Column(name="asuntoA")
	@NotEmpty
	private String asuntoA; 
	
	@Column(name="mensajeA")
	@NotEmpty
	private String mensajeA;
	
	@ManyToOne
	@JoinColumn(name="receptor")
	private User receptor;
	
	@ManyToOne
	@JoinColumn(name="emisor")
	private User emisor;
	
	
}
