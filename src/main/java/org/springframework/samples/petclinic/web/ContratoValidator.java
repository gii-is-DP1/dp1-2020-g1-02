package org.springframework.samples.petclinic.web;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;
import org.springframework.samples.petclinic.model.Contrato;
import org.springframework.samples.petclinic.model.ContratoServicio;
import org.springframework.samples.petclinic.model.ContratoTrabajador;
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
		ContratoServicio cS = (ContratoServicio) obj;
		LocalDate fechaPago = cS.getFechapago();
		Boolean periodoPrueba = cS.getPeriodoPrueba();
		Double prePrep = cS.getPresupuesto().getPrecio();
		ContratoTrabajador cT = (ContratoTrabajador) obj;
		Double sueldo = cT.getSueldo();
		String nomTrab = cT.getTrabajador().getNombre();
	
		if (fechainicial == null || !Pattern.matches("yyyy/MM/dd", fechainicial.format(DateTimeFormatter.ofPattern("yyyy/MM/dd")))) {
			errors.rejectValue("fechainicial", REQUIRED + " debe tener 4 dígitos el año, 2 dígitos el mes, 2 dígitos el día y estar separados por barra", REQUIRED + " debe tener 4 dígitos el año, 2 dígitos el mes, 2 dígitos el día y estar separados por barra");
		}

		if (fechafinal == null || !Pattern.matches("yyyy/MM/dd", fechafinal.format(DateTimeFormatter.ofPattern("yyyy/MM/dd")))) {
			errors.rejectValue("fechafinal", REQUIRED + " debe tener 4 dígitos el año, 2 dígitos el mes, 2 dígitos el día y estar separados por barra", REQUIRED + " debe tener 4 dígitos el año, 2 dígitos el mes, 2 dígitos el día y estar separados por barra");
		}
		
		if (fechaPago == null || !Pattern.matches("yyyy/MM/dd", fechaPago.format(DateTimeFormatter.ofPattern("yyyy/MM/dd")))) {
			errors.rejectValue("fechaPago", REQUIRED + " debe tener 4 dígitos el año, 2 dígitos el mes, 2 dígitos el día y estar separados por barra", REQUIRED + " debe tener 4 dígitos el año, 2 dígitos el mes, 2 dígitos el día y estar separados por barra");
		}
		
		if(periodoPrueba == null) {
			errors.rejectValue("periodoPrueba", REQUIRED + "el periodo de prueba de un contrato no puede ser nulo", REQUIRED + "el periodo de prueba de un contrato no puede ser nulo");
		}
		
		if(prePrep == null) {
			errors.rejectValue("prePrep", REQUIRED + "El precio del presupuesto del contrato no puede ser nulo", REQUIRED + "El precio del presupuesto del contrato no puede ser nulo");
		}
		
		if(sueldo == null) {
			errors.rejectValue("sueldo", REQUIRED + "El sueldo de un trabajador no puede ser nulo", REQUIRED + "El sueldo de un trabajador no puede ser nulo");
		}
		
		if (nomTrab == null || nomTrab.trim().equals("")) {
			errors.rejectValue("nomTrab", REQUIRED, REQUIRED);
		}
	}

	
	@Override
	public boolean supports(Class<?> clazz) {
		return Contrato.class.isAssignableFrom(clazz);
	}
	
}
