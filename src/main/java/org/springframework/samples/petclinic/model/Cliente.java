package org.springframework.samples.petclinic.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="cliente")
public class Cliente extends PersonaEntity {
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="cliente")
    private Set<Instalacion> instalaciones;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="cliente")
    private Set<Servicio> servicios;
}
