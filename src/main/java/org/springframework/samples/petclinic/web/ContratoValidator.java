package org.springframework.samples.petclinic.web;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

import org.springframework.samples.petclinic.model.Contrato;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ContratoValidator implements Validator {

private static final String REQUIRED = "required";
	
	@Override
	public void validate(Object obj, Errors errors) {
		Contrato contrato = (Contrato) obj;
		LocalDate fechainicial = contrato.getFechainicial();
		LocalDate fechafinal = contrato.getFechafinal();
	
		if (fechainicial == null || !Pattern.matches("yyyy/MM/dd", fechainicial.format(DateTimeFormatter.ofPattern("yyyy/MM/dd")))) {
			errors.rejectValue("fechainicial", REQUIRED + " debe tener 4 dígitos el año, 2 dígitos el mes, 2 dígitos el día y estar separados por barra", REQUIRED + " debe tener 4 dígitos el año, 2 dígitos el mes, 2 dígitos el día y estar separados por barra");
		}

		if (fechafinal == null || !Pattern.matches("yyyy/MM/dd", fechafinal.format(DateTimeFormatter.ofPattern("yyyy/MM/dd")))) {
			errors.rejectValue("fechafinal", REQUIRED + " debe tener 4 dígitos el año, 2 dígitos el mes, 2 dígitos el día y estar separados por barra", REQUIRED + " debe tener 4 dígitos el año, 2 dígitos el mes, 2 dígitos el día y estar separados por barra");
		}
	}

	
	@Override
	public boolean supports(Class<?> clazz) {
		return Contrato.class.isAssignableFrom(clazz);
	}
	
}
