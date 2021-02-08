package org.springframework.samples.petclinic.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.springframework.samples.petclinic.customvalidators.ConfirmPassword;
import org.springframework.samples.petclinic.customvalidators.ContactNumberConstraint;
import org.springframework.samples.petclinic.customvalidators.PasswordConstraint;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name="proveedor")
public class Proveedor extends NamedEntity{
	
    @ContactNumberConstraint
    private String telefono;
    
    @Email
    @NotEmpty
    private String email;
    
    @NotEmpty
    private String direccion;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy="proveedor")
    private List<Oferta> ofertas;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy="proveedor")
    private List<Factura> facturas;
    
    
    //
	@OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "username", referencedColumnName = "username")
	@PasswordConstraint
	@ConfirmPassword
	private User user;
	//
	
	public User getUser() {
		return user;
	}

}