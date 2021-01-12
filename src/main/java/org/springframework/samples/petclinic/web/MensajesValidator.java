package org.springframework.samples.petclinic.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.MensajesAdmin;
import org.springframework.samples.petclinic.service.MensajesAdminService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class MensajesValidator implements Validator {

	private static final String REQUIRED = "required";
	
	@Autowired
	private MensajesAdminService mensajesAdminService;
	
	public Boolean mensajeConMismoAsunto(String name) {
		return true;
	}
	
	@Override
	public void validate(Object obj, Errors errors) {
		MensajesAdmin msj = (MensajesAdmin) obj;
		String asunto = msj.getAsuntoA();
		String mensaje = msj.getMensajeA();

		if (asunto == null || asunto.trim().equals("")) {
			errors.rejectValue("asunto", REQUIRED, REQUIRED);
		}

		if (mensaje == null || mensaje.trim().equals("")) {
			errors.rejectValue("direccion", REQUIRED , REQUIRED );
		}

		
	}


	@Override
	public boolean supports(Class<?> clazz) {
		return MensajesAdmin.class.isAssignableFrom(clazz);
	}
}
