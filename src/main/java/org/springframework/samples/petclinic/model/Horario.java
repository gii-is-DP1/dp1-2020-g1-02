package org.springframework.samples.petclinic.model;


import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="horario")
public class Horario extends BaseEntity {
	
	
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@NotNull
	@FutureOrPresent
	private LocalDate fecha;
	
    @DateTimeFormat(pattern = "HH:mm")
    @Temporal(TemporalType.TIME)
    @NotNull
    private Date hora_inicio;

    @DateTimeFormat(pattern = "HH:mm")
    @Temporal(TemporalType.TIME)
    @NotNull
    private Date hora_fin;
    	
	@ManyToOne
	@JoinColumn(name="trabajador")
	private Trabajador trabajador;
	
    @NotNull
    private String descripcion;

}
