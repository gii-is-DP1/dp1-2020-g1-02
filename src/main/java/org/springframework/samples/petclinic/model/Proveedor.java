package org.springframework.samples.petclinic.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Data;


@Entity
@Data
@Table(name="proveedor")
public class Proveedor extends NamedEntity{
	
    @Column(name="telefono")
    @NotEmpty
    private String telefono;
    
    @Column(name="email")
    @NotEmpty
    private String email;
    
    @Column(name="direccion")
    @NotEmpty
    private String direccion;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy="proveedor")
    private Set<Factura> facturas;
    
    //
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "username", referencedColumnName = "username")
	private User user;
	//
	
	public User getUser() {
		return user;
	}

}