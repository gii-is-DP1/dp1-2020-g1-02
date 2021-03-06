package org.springframework.samples.petclinic.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="administrador")
public class Administrador extends PersonaEntity{
	
    
    @NotNull
    private TipoCategoria tipocategoria; 
    
    //
	@OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "username", referencedColumnName = "username")
	private User user;
	//
	
	public User getUser() {
		return user;
	}

    //@OneToMany(cascade = CascadeType.ALL, mappedBy="administrador")
    //private Set<MensajesAdmin> mensajesA;

	
}
