package org.springframework.samples.petclinic.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;


@Entity
@Setter
@Getter
@Table(name="pedido")
public class Pedido extends BaseEntity{

    @Column(name="fecha")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    @NotNull
    private LocalDate fechaPedido;
    
    @Min(0)
    @Column(name="cantidad")
    @NotNull
    private Integer cantidadProducto;
    
    @ManyToOne
    @JoinColumn(name = "oferta_id")
    private Oferta oferta;
    
    @OneToOne(optional=true, cascade = CascadeType.ALL, mappedBy="pedido")
    private Factura factura;

}