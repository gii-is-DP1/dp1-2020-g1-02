package org.springframework.samples.petclinic.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Data
@Table(name="mensaje")
public class Mensaje extends BaseEntity{
	
	@Column(name="fecha")
	@NotNull
	private LocalDate fecha;
	
	@Column(name="asunto")
	@NotEmpty
	private String asunto; 
	
	@Column(name="cuerpo")
	@NotEmpty
	private String cuerpo;
	
	@ManyToOne
	@JoinColumn(name="receptor")
	private User receptor;
	
	@ManyToOne
	@JoinColumn(name="emisor")
	private User emisor;
	
	
}
