package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="administrador")
public class Administrador extends BaseEntity {
	
	@Column(name="nombre")
    @NotNull
	private String nombre;
	
	@Column(name="apellidos")
    @NotNull
	private String apellidos;
	
    @Column(name="tipocategoria")
    @NotNull
    private TipoCategoria tipocategoria; 
	
}
