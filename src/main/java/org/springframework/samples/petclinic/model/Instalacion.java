package org.springframework.samples.petclinic.model;

import javax.persistence.Entity;
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
@Table(name="instalacion")
public class Instalacion extends BaseEntity {

    @NotNull
    private String lugar;
	
    @Min(0)
    @NotNull
    private Double dimension;
	
	@ManyToOne
    @JoinColumn(name="cliente_id")
    private Cliente cliente;
}
