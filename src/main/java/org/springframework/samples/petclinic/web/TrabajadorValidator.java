package org.springframework.samples.petclinic.web;

import java.util.Iterator;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Trabajador;
import org.springframework.samples.petclinic.service.TrabajadorService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class TrabajadorValidator implements Validator  {

private static final String REQUIRED = "required";
	
	@Autowired
	private TrabajadorService trabajadorService;
	
	
	public Boolean trabajadorConMismoDNI(String dni) {
		dni = dni.toLowerCase();
		Boolean result = false;
		Iterator<Trabajador> itTrabajador = this.trabajadorService.findAll().iterator();
		while(itTrabajador.hasNext()) {
			Trabajador trab = itTrabajador.next();
			String pruebaDni = trab.getDni().toLowerCase();
			if(pruebaDni.equals(dni)) {
				result = true;
			}
		}
		return result;
	}
	
	@Override
	public void validate(Object obj, Errors errors) {
		Trabajador trab = (Trabajador) obj;
		String name = trab.getNombre();
		String dni = trab.getDni();
		String telefono = trab.getTelefono();

		if (name == null || name.trim().equals("")) {
			errors.rejectValue("name", REQUIRED, REQUIRED);
		}

		if (dni == null || dni.trim().equals("") || !Pattern.matches("[0-9]{8}[A-Z]{1}", dni)) {
			errors.rejectValue("dni", REQUIRED + " debe tener 8 digitos y un caracter en mayúscula al final", REQUIRED + " debe tener 8 digitos y un caracter en mayúscula al final");
		}

		if (telefono == null || telefono.trim().equals("") || !Pattern.matches("[0-9]{9}", telefono)) {
			errors.rejectValue("telefono", REQUIRED + " debe tener 9 digitos", REQUIRED + " debe tener 9 digitos");
		}
	}

	
	@Override
	public boolean supports(Class<?> clazz) {
		return Trabajador.class.isAssignableFrom(clazz);
	}
}
