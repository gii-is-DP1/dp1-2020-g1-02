package org.springframework.samples.petclinic.customvalidators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.samples.petclinic.model.Horario;

public class HorarioValidator implements ConstraintValidator<HorarioValidatorConstraint, Horario> {

    @Override
    public void initialize(HorarioValidatorConstraint contactNumber) {
    }
	
	@Override
	public boolean isValid(Horario horario, ConstraintValidatorContext context) {
		return horario.getHora_inicio() != null && horario.getHora_fin() != null &&
				horario.getHora_fin().isAfter(horario.getHora_inicio());
	}

}
