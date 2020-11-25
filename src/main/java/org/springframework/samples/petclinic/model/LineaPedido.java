package org.springframework.samples.petclinic.model;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Data;


@Entity
@Data
@Table(name="lineaPedido")
public class LineaPedido extends BaseEntity{

    @Column(name="cantidad")
    @NotEmpty
    private Integer cantidad;
    
    @Column(name="precio")
    @NotEmpty
    private Double precio;
    

}
