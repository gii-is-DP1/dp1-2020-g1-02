package org.springframework.samples.petclinic.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.sun.istack.NotNull;

import lombok.Data;


@Entity
@Data
@Table(name="trabajador")
public class Trabajador extends PersonaEntity {

    @Column(name="tipocategoria")
    @NotEmpty
    private String tipocategoria;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy="trabajador")
    private Set<ContratoTrabajador> contratos; 
    
}