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
@Table(name="registro_hora")
public class RegistroHoras extends BaseEntity {
	
	@Column(name="hora_entrada")
    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm")
    @NotNull
    private LocalDateTime hora_entrada;

    @Column(name="hora_salida")
    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm")
    @NotNull
    private LocalDateTime hora_salida;
    
    @ManyToOne
    @JoinColumn(name="trabajador")
    private Trabajador trabajador;

}
