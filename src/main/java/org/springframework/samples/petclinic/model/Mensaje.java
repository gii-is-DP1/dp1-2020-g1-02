package org.springframework.samples.petclinic.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="mensaje")
public class Mensaje extends BaseEntity{
	
	@NotNull
	private LocalDate fecha;
	
	@Size(min=0, max=50)
	@NotEmpty
	private String asunto; 
	
	@NotEmpty
	private String cuerpo;
	
	@NotNull
	private Boolean leido;
	
	@ManyToMany(mappedBy = "mensajesRecibidos", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	@NotNull
	private List<User> receptores;
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="emisor")
	@NotNull
	private User emisor;
	
}
