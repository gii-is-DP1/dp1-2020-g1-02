package org.springframework.samples.petclinic.model;

import java.util.List;
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
	
    @NotEmpty
    private String telefono;
    
    @NotEmpty
    private String email;
    
    @NotEmpty
    private String direccion;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy="proveedor")
    private List<Oferta> ofertas;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy="proveedor")
    private List<Factura> facturas;
    
    //
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "username", referencedColumnName = "username")
	private User user;
	//
	
	public User getUser() {
		return user;
	}

}