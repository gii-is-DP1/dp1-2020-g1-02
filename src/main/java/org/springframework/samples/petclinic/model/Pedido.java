package org.springframework.samples.petclinic.model;

import java.time.LocalDate;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Data;


@Entity
@Data
@Table(name="pedidos")
public class Pedido extends NamedEntity{

    @Column(name="fecha")
    @NotEmpty
    private LocalDate fechaPedido;
    

}