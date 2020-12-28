package org.springframework.samples.petclinic.model;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Entity
@Data
@Table(name="horario")
public class Horario extends BaseEntity {
	
	
	@Column(name="horaInicio")
    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm")
    @NotEmpty
    private LocalDateTime horaInicio;

    @Column(name="horaFin")
    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm")
    @NotEmpty
    private LocalDateTime horaFin;
    	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="trabajador_id")
	private Trabajador trabajador;
	
	@Column(name="descripcion")
    @NotEmpty
    private String descripcion;

}
