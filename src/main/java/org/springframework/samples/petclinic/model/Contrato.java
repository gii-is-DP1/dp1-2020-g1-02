package org.springframework.samples.petclinic.model;

import java.time.LocalDate;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.samples.petclinic.web.FechaValidatorConstraint;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
@FechaValidatorConstraint
public class Contrato  extends BaseEntity{
	
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@NotNull
	private LocalDate fechainicial;
	
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@NotNull
	private LocalDate fechafinal;

}
