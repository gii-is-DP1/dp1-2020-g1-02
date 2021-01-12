package org.springframework.samples.petclinic.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="administrador")
public class Administrador extends PersonaEntity{
	
    @Column(name="tipocategoria")
    @NotNull
    private TipoCategoria tipocategoria; 
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy="administrador")
    private Set<MensajesAdmin> mensajesA;
	
}
