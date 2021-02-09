package org.springframework.samples.petclinic.customvalidators;

import java.time.LocalDate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FechaInicioValidator implements ConstraintValidator<FechaInicioConstraint, LocalDate>{
	
	@Override
	public void initialize(FechaInicioConstraint fecha) {}
	
	@Override
	public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		LocalDate hoy = LocalDate.now();
		return value.equals(hoy) || value.isAfter(hoy);
	}

}
