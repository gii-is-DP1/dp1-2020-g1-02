package org.springframework.samples.petclinic.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name="contratotrabajador")
public class ContratoTrabajador extends Contrato {
	
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="trabajador_id")
    private Trabajador trabajador;
    
    @Min(0)
    @NotNull
    private Double sueldo;
    
    
    
}