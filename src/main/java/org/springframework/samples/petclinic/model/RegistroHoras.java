package org.springframework.samples.petclinic.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="registro_hora")
public class RegistroHoras extends BaseEntity {
	
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@NotNull
	@FutureOrPresent
	private LocalDate fecha;
	
    @DateTimeFormat(pattern = "HH:mm")
    @NotNull
    private LocalTime hora_inicio;

    @DateTimeFormat(pattern = "HH:mm")
    @NotNull
    private LocalTime hora_fin;
    
    @ManyToOne
    @JoinColumn(name="trabajador")
    private Trabajador trabajador;

}
