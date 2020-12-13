package org.springframework.samples.petclinic.model;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Data;


@Entity
@Data
@Table(name="pedidos")
public class Pedido extends BaseEntity{


    @Column(name="fecha")
    @NotEmpty
    private LocalDate fechaPedido;
    
    @OneToOne(optional=true)
    @JoinColumn(name="factura_id")
    private Factura factura;

}