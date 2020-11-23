package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.sun.istack.NotNull;

import lombok.Data;

@Data
@Entity
@Table(name="administrador")
public class Administrador extends BaseEntity {
	
	@Column(name="nombre")
    @NotEmpty
	private String nombre;
	
	@Column(name="apellidos")
    @NotEmpty
	private String apellidos;
	
    @Column(name="tipocategoria")
    @NotNull
    private TipoCategoria tipocategoria; 
	
}
