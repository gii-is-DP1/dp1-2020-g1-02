package org.springframework.samples.petclinic.web;

import java.util.Iterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Instalacion;
import org.springframework.samples.petclinic.service.InstalacionService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class InstalacionValidator implements Validator {

	private static final String REQUIRED = "required";
	
	@Autowired
	private InstalacionService instalacionService;
	
	
	public Boolean instalacionDeUnMismoCliente(String nombre) {
		nombre = nombre.toLowerCase();
		Boolean result = false;
		Iterator<Instalacion> itInstalacion = this.instalacionService.findAll().iterator();
		while(itInstalacion.hasNext()) {
			Instalacion instalacion = itInstalacion.next();
			String nombreCli = instalacion.getCliente().getNombre().toLowerCase();
			if(nombreCli.equals(nombre)) {
				result = true;
			}
		}
		return result;
	}
	
	@Override
	public void validate(Object obj, Errors errors) {
		Instalacion instalacion = (Instalacion) obj;
		String lugar = instalacion.getLugar();
		String nombreCli = instalacion.getCliente().getNombre();

		if (lugar == null || lugar.trim().equals("")) {
			errors.rejectValue("lugar", REQUIRED, REQUIRED);
		}
		
		if (nombreCli == null || nombreCli.trim().equals("")) {
			errors.rejectValue("nombreCli", REQUIRED, REQUIRED);
		}

	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Instalacion.class.isAssignableFrom(clazz);
	}
}
