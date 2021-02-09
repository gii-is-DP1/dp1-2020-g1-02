package org.springframework.samples.petclinic.web;

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
		if (nombreTrab == null || nombreTrab.trim().equals("")) {
			errors.rejectValue("nombreTrab", REQUIRED, REQUIRED);
		}
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Horario.class.isAssignableFrom(clazz);
	}

}
