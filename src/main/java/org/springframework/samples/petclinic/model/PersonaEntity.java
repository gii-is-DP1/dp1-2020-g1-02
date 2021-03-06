
package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.samples.petclinic.customvalidators.ContactNumberConstraint;
import org.springframework.samples.petclinic.customvalidators.DNIConstraint;

@MappedSuperclass
public class PersonaEntity extends BaseEntity {

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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	@Override
	public String toString() {
		return "PersonaEntity [nombre=" + nombre + ", apellidos=" + apellidos + ", dni=" + dni + ", telefono="
				+ telefono + ", direccion=" + direccion + ", correo=" + correo + "]";
	}

    
    
	

}
