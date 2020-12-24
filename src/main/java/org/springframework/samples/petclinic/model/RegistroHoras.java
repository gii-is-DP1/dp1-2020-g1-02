package org.springframework.samples.petclinic.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Entity
@Data
@Table(name="registroHoras")
public class RegistroHoras extends BaseEntity {
	
	@Column(name="horaEntrada")
    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm")
    @NotEmpty
    private LocalDateTime horaEntrada;

    @Column(name="horaSalida")
    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm")
    @NotEmpty
    private LocalDateTime horaSalida;
    
    @ManyToOne
    //@JoinColumn(name="trabajador_id")
    private Trabajador trabajador;

}
