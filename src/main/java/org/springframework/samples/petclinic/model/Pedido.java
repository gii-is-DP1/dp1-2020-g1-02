package org.springframework.samples.petclinic.model;

import java.time.LocalDate;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;


@Entity
@Data
@Table(name="pedido")
public class Pedido extends BaseEntity{

    @Column(name="fecha")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    @NotNull
    private LocalDate fechaPedido;
    
    @Column(name="cantidad")
    @NotNull
    private Integer cantidadProducto;
    
    @ManyToOne
    @JoinColumn(name = "oferta_id")
    private Oferta oferta;
    
    @OneToOne(optional=true)
    @JoinColumn(name="factura_id")
    private Factura factura;

}