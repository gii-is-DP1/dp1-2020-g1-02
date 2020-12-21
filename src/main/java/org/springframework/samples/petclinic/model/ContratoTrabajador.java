package org.springframework.samples.petclinic.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name="contratotrabajador")
public class ContratoTrabajador extends BaseEntity {

    @Column(name="fechainicial")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    @NotNull
    private LocalDate fechainicial;

    @Column(name="fechafinal")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    @NotNull
    private LocalDate fechafinal;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="trabajador_id")
    private Trabajador trabajador;
    
    @Column(name="sueldo")
    @NotNull
    private Double sueldo;
    
    
    
}