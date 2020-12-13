package org.springframework.samples.petclinic.model;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="cliente")
public class Cliente extends PersonaEntity {
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="cliente")
    private Set<Instalacion> instalaciones;
}
