package org.springframework.samples.petclinic.model;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Data;


@Entity
@Data
@Table(name="proveedor")
public class Proveedor extends BaseEntity{
	
	 @Column(name="name")
	 @NotEmpty
	 private String name;
	
    @Column(name="telefono")
    @NotEmpty
    private String telefono;
    
    @Column(name="email")
    @NotEmpty
    private String email;
    
    @Column(name="direccion")
    @NotEmpty
    private String direccion;

}