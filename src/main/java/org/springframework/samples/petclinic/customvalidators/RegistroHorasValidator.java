package org.springframework.samples.petclinic.customvalidators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.samples.petclinic.model.RegistroHoras;

public class RegistroHorasValidator implements ConstraintValidator<RegistroHorasConstraint, RegistroHoras> {

    @Override
    public void initialize(RegistroHorasConstraint contactNumber) {
    }
	
	@Override
	public boolean isValid(RegistroHoras horas, ConstraintValidatorContext context) {
		return horas.getHora_fin().isAfter(horas.getHora_inicio());
	}

}