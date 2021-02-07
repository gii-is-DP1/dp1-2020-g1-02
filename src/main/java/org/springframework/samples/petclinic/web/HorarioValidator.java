package org.springframework.samples.petclinic.web;

import java.time.LocalTime;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Horario;
import org.springframework.samples.petclinic.service.HorarioService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class HorarioValidator implements Validator {

	private static final String REQUIRED = "required";
	
	@Autowired
	private HorarioService horarioService;
	
	
	public Boolean horarioDeUnMismoTrabajador(String nombre) {
		nombre = nombre.toLowerCase();
		Boolean result = false;
		Iterator<Horario> itHorario = this.horarioService.findAll().iterator();
		while(itHorario.hasNext()) {
			Horario horario = itHorario.next();
			String nombreTrab = horario.getTrabajador().getNombre().toLowerCase();
			if(nombreTrab.equals(nombre)) {
				result = true;
			}
		}
		return result;
	}
	
	@Override
	public void validate(Object obj, Errors errors) {
		Horario horario = (Horario) obj;
		String nombreTrab = horario.getTrabajador().getNombre();
		LocalTime hora_inicio = horario.getHora_inicio();
		LocalTime hora_fin = horario.getHora_fin();
		
		if (nombreTrab == null || nombreTrab.trim().equals("")) {
			errors.rejectValue("nombreTrab", REQUIRED, REQUIRED);
		}

//		if (hora_inicio == null || !Pattern.matches("yyyy/MM/dd HH:mm", hora_inicio.format(Date.ofPattern("HH:mm")))) {
//			errors.rejectValue("hora_inicio", REQUIRED + " debe tener 4 dígitos el año, 2 dígitos el mes, 2 dígitos el día y estar separados por barra. También debe de tener 2 dígitos la hora, 2 dígitos el minuto y estar separados por 2 puntos", REQUIRED + " debe tener 4 dígitos el año, 2 dígitos el mes, 2 dígitos el día y estar separados por barra. También debe de tener 2 dígitos la hora, 2 dígitos el minuto y estar separados por 2 puntos");
//		}
//
//		if (hora_fin == null || !Pattern.matches("yyyy/MM/dd HH:mm", hora_fin.format(Date.ofPattern("HH:mm")))) {
//			errors.rejectValue("hora_fin", REQUIRED + " debe tener 4 dígitos el año, 2 dígitos el mes, 2 dígitos el día y estar separados por barra. También debe de tener 2 dígitos la hora, 2 dígitos el minuto y estar separados por 2 puntos", REQUIRED + " debe tener 4 dígitos el año, 2 dígitos el mes, 2 dígitos el día y estar separados por barra. También debe de tener 2 dígitos la hora, 2 dígitos el minuto y estar separados por 2 puntos");
//		}
	}

	
	@Override
	public boolean supports(Class<?> clazz) {
		return Horario.class.isAssignableFrom(clazz);
	}

}
