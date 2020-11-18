package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
@Entity
@Table(name="cliente")
public class Cliente extends NamedEntity {

	@Column(name="nombre")
    @NotEmpty
	private String nombre;
	
	@Column(name="apellidos")
    @NotEmpty
	private String apellidos;
	
	@Column(name="telefono")
    @NotEmpty
	private String telefono;
	
	@Column(name="direccion")
    @NotEmpty
	private String direccion;
	
	@Column(name="dni")
    @NotEmpty
	private String dni;
	
	@Column(name="email")
    @NotEmpty
	private String email;
}
