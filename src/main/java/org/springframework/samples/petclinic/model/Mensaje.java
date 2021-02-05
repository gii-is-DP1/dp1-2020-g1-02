package org.springframework.samples.petclinic.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
	
	@NotNull
	private LocalDate fecha;
	
	@NotEmpty
	private String asunto; 
	
	@NotEmpty
	private String cuerpo;
	
	@NotNull
	private Boolean leido;
	
	@ManyToMany(mappedBy = "mensajesRecibidos")
	private List<User> receptores;
	
	
	@ManyToOne
	@JoinColumn(name="emisor")
	private User emisor;
	
}
