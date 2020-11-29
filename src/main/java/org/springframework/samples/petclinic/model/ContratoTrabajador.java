package org.springframework.samples.petclinic.model;

import java.time.LocalDate;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;


@Entity
@Data
@Table(name="contratotrabajador")
public class ContratoTrabajador extends BaseEntity {

    @Column(name="fechainicial")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    @NotEmpty
    private LocalDate fechainicial;

    @Column(name="fechafinal")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    @NotEmpty
    private LocalDate fechafinal;
    
    @ManyToOne
    @JoinColumn(name="trabajador_id")
    private Trabajador trabajador;
    
    @Column(name="sueldo")
    @NotEmpty
    private Double sueldo;
    
}