package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Entity
@Data
@Table(name="instalacion")
public class Instalacion extends BaseEntity {

	@Column(name="lugar")
    @NotEmpty
    private String lugar;
	
	@Column(name="dimension")
    @NotEmpty
    private Double dimension;
	
	@ManyToOne
    //@JoinColumn(name="cliente_id")
    private Cliente cliente;
}
