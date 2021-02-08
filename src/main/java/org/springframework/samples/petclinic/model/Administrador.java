package org.springframework.samples.petclinic.model;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.samples.petclinic.customvalidators.ConfirmPassword;
import org.springframework.samples.petclinic.customvalidators.PasswordConstraint;

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
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "username", referencedColumnName = "username")
	@PasswordConstraint
	@ConfirmPassword
	private User user;
	//
	
	public User getUser() {
		return user;
	}

    //@OneToMany(cascade = CascadeType.ALL, mappedBy="administrador")
    //private Set<MensajesAdmin> mensajesA;

	
}
