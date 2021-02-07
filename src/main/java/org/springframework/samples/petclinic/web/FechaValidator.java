package org.springframework.samples.petclinic.web;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.samples.petclinic.model.Contrato;

public class FechaValidator implements ConstraintValidator<FechaValidatorConstraint, Contrato> {

    @Override
    public void initialize(FechaValidatorConstraint contactNumber) {
    }
	
	@Override
	public boolean isValid(Contrato contrato, ConstraintValidatorContext context) {
		return contrato.getFechafinal().isAfter(contrato.getFechainicial());
	}

}
