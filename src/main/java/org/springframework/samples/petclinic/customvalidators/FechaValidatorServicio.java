package org.springframework.samples.petclinic.customvalidators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.samples.petclinic.model.Servicio;

public class FechaValidatorServicio implements ConstraintValidator<FechaValidatorServicioConstraint, Servicio> {

    @Override
    public void initialize(FechaValidatorServicioConstraint contactNumber) {
    }
	
	@Override
	public boolean isValid(Servicio servicio, ConstraintValidatorContext context) {
		return servicio.getFechafin() != null && servicio.getFechainicio() != null &&
				servicio.getFechafin().isAfter(servicio.getFechainicio());
	}

}
