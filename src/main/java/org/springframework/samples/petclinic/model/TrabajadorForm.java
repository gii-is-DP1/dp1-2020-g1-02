package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.samples.petclinic.customvalidators.ContactNumberConstraint;
import org.springframework.samples.petclinic.customvalidators.DNIConstraint;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrabajadorForm extends UserForm{
	
	@Size(min = 3, max = 50)
    @NotEmpty
	private String nombre;
    
    @Size(min = 3, max = 50)
    @NotEmpty
   	private String apellidos;
    
    @Column(unique=true)
    @DNIConstraint
    private String dni;
    
    @ContactNumberConstraint
    private String telefono;
    
    @Size(min=3, max=50)
    private String direccion;
    
    @Email
    @NotBlank
    private String correo;
    
    @NotNull
    private TipoCategoria tipocategoria;

}
