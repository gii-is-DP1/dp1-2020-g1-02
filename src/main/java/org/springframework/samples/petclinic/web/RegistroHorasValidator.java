package org.springframework.samples.petclinic.web;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
		LocalDateTime hora_entrada = registroHoras.getHora_entrada();
		LocalDateTime hora_salida = registroHoras.getHora_salida();

		if (nombreTrab == null || nombreTrab.trim().equals("")) {
			errors.rejectValue("nombreTrab", REQUIRED, REQUIRED);
		}
		
		if (hora_entrada == null || !Pattern.matches("yyyy/MM/dd HH:mm", hora_entrada.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm")))) {
			errors.rejectValue("hora_entrada", REQUIRED + " debe tener 4 dígitos el año, 2 dígitos el mes, 2 dígitos el día y estar separados por barra. También debe de tener 2 dígitos la hora, 2 dígitos el minuto y estar separados por 2 puntos", REQUIRED + " debe tener 4 dígitos el año, 2 dígitos el mes, 2 dígitos el día y estar separados por barra. También debe de tener 2 dígitos la hora, 2 dígitos el minuto y estar separados por 2 puntos");
		}

		if (hora_salida == null || !Pattern.matches("yyyy/MM/dd HH:mm", hora_salida.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm")))) {
			errors.rejectValue("hora_salida", REQUIRED + " debe tener 4 dígitos el año, 2 dígitos el mes, 2 dígitos el día y estar separados por barra. También debe de tener 2 dígitos la hora, 2 dígitos el minuto y estar separados por 2 puntos", REQUIRED + " debe tener 4 dígitos el año, 2 dígitos el mes, 2 dígitos el día y estar separados por barra. También debe de tener 2 dígitos la hora, 2 dígitos el minuto y estar separados por 2 puntos");
		}

	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return RegistroHoras.class.isAssignableFrom(clazz);
	}
}
