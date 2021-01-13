package org.springframework.samples.petclinic.web;

import java.util.Iterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Curriculum;
import org.springframework.samples.petclinic.service.CurriculumService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CurriculumValidator implements Validator {

	private static final String REQUIRED = "required";
	
	@Autowired
	private CurriculumService curriculumService;
	
	
	public Boolean curriculumDeUnMismoTrabajador(String nombre) {
		nombre = nombre.toLowerCase();
		Boolean result = false;
		Iterator<Curriculum> itCurriculum = this.curriculumService.findAll().iterator();
		while(itCurriculum.hasNext()) {
			Curriculum curriculum = itCurriculum.next();
			String nombreTrab = curriculum.getTrabajador().getNombre().toLowerCase();
			if(nombreTrab.equals(nombre)) {
				result = true;
			}
		}
		return result;
	}
	
	@Override
	public void validate(Object obj, Errors errors) {
		Curriculum curriculum = (Curriculum) obj;
		String nombre = curriculum.getNombre();
		String nombreTrab = curriculum.getTrabajador().getNombre();

		if (nombre == null || nombre.trim().equals("")) {
			errors.rejectValue("nombre", REQUIRED, REQUIRED);
		}
		
		if (nombreTrab == null || nombreTrab.trim().equals("")) {
			errors.rejectValue("nombreTrab", REQUIRED, REQUIRED);
		}

	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Curriculum.class.isAssignableFrom(clazz);
	}
}
