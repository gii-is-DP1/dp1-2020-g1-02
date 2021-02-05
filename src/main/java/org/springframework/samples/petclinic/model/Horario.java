package org.springframework.samples.petclinic.model;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name="horario")
public class Horario extends BaseEntity {
	
    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm")
    @NotNull
    private LocalDateTime hora_inicio;

    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm")
    @NotNull
    private LocalDateTime hora_fin;
    	
	@ManyToOne
	@JoinColumn(name="trabajador")
	private Trabajador trabajador;
	
    @NotNull
    private String descripcion;

}
