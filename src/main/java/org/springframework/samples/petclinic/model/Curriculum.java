package org.springframework.samples.petclinic.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.samples.petclinic.customvalidators.ContactNumberConstraint;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="curriculum")
public class Curriculum extends BaseEntity {
	
    @NotBlank
    @Size(min = 3, max = 50)
	private String nombre;
    
    
    @NotBlank
    @Size(min = 3, max = 50)
	private String apellidos;
    
    @ContactNumberConstraint
	private String telefono;
    
    @NotBlank
    @Email
    @Size(min = 5, max = 50)
	private String correo;
    
    @NotBlank
    @Size(max = 300)
	private String descripcion;
	
	@NotNull
	private TipoCategoria tipocategoria;
	
	
	@OneToOne( mappedBy="curriculum")
	private Trabajador trabajador;
	
}
