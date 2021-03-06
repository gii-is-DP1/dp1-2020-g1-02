package org.springframework.samples.petclinic.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.samples.petclinic.customvalidators.ContactNumberConstraint;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProveedorForm extends UserForm{
	
	@Size(min = 3, max = 50)
    @NotEmpty
	private String nombre;
    
    @ContactNumberConstraint
    private String telefono;
    
    @Size(min=3, max=50)
    private String direccion;
    
    @Email
    @NotBlank
    private String correo;
}
