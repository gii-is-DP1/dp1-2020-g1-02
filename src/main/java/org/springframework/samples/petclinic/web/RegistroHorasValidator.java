package org.springframework.samples.petclinic.web;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Iterator;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.RegistroHoras;
import org.springframework.samples.petclinic.service.RegistroHorasService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class RegistroHorasValidator implements Validator {

private static final String REQUIRED = "required";
	
	@Autowired
	private RegistroHorasService registroHorasService;
	
	
	public Boolean registroHorasDeUnMismoTrabajador(String nombre) {
		nombre = nombre.toLowerCase();
		Boolean result = false;
		Iterator<RegistroHoras> itRegistroHoras = this.registroHorasService.findAll().iterator();
		while(itRegistroHoras.hasNext()) {
			RegistroHoras registroHoras = itRegistroHoras.next();
			String nombreTrab = registroHoras.getTrabajador().getNombre().toLowerCase();
			if(nombreTrab.equals(nombre)) {
				result = true;
			}
		}
		return result;
	}
	
	@Override
	public void validate(Object obj, Errors errors) {
		RegistroHoras registroHoras = (RegistroHoras) obj;
		String nombreTrab = registroHoras.getTrabajador().getNombre();
		LocalDate fecha = registroHoras.getFecha();

		if (nombreTrab == null || nombreTrab.trim().equals("")) {
			errors.rejectValue("nombreTrab", REQUIRED, REQUIRED);
		}
		
		if (fecha == null || !Pattern.matches("yyyy/MM/dd", fecha.format(DateTimeFormatter.ofPattern("yyyy/MM/dd")))) {
			errors.rejectValue("fecha", REQUIRED + " debe tener 4 dígitos el año, 2 dígitos el mes, 2 dígitos el día y estar separados por barra.", REQUIRED + " debe tener 4 dígitos el año, 2 dígitos el mes, 2 dígitos el día y estar separados por barra");
		}

	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return RegistroHoras.class.isAssignableFrom(clazz);
	}
}
