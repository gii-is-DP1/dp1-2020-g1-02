package org.springframework.samples.petclinic.model;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Data;


@Entity
@Data
@Table(name="trabajador")
public class Trabajador extends NamedEntity{

    @Column(name="apellidos")
    @NotEmpty
    private String apellidos;

    @Column(name="dni")
    @NotEmpty
    private String dni;

}