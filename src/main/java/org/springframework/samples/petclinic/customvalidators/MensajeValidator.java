package org.springframework.samples.petclinic.customvalidators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.samples.petclinic.model.Mensaje;

public class MensajeValidator implements ConstraintValidator<MensajeConstraint, Mensaje> {

	@Override
	public void initialize(MensajeConstraint psw) { }
	  
	@Override
	public boolean isValid(Mensaje value, ConstraintValidatorContext context) {
		return value.getReceptores() != null;
	}

}
