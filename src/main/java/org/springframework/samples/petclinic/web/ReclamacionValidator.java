package org.springframework.samples.petclinic.web;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Reclamacion;
import org.springframework.samples.petclinic.service.ReclamacionService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ReclamacionValidator implements Validator {

	private static final String REQUIRED = "required";
	
	@Autowired
	private ReclamacionService reclamacionService;
	
	
	public Boolean reclamacionDeUnMismoCliente(String nombre) {
		nombre = nombre.toLowerCase();
		Boolean result = false;
		Iterator<Reclamacion> itReclamacion = this.reclamacionService.findAll().iterator();
		while(itReclamacion.hasNext()) {
			Reclamacion reclamacion = itReclamacion.next();
			String nombreCli = reclamacion.getCliente().getNombre().toLowerCase();
			if(nombreCli.equals(nombre)) {
				result = true;
			}
		}
		return result;
	}
	
	public Boolean reclamacionDeUnMismoServicio(String lugar) {
		lugar = lugar.toLowerCase();
		Boolean result = false;
		Iterator<Reclamacion> itReclamacion = this.reclamacionService.findAll().iterator();
		while(itReclamacion.hasNext()) {
			Reclamacion reclamacion = itReclamacion.next();
			String lugarServ = reclamacion.getServicio().getLugar().toLowerCase();
			if(lugarServ.equals(lugar)) {
				result = true;
			}
		}
		return result;
	}
	
	@Override
	public void validate(Object obj, Errors errors) {
		Reclamacion reclamacion = (Reclamacion) obj;
		LocalDate fecha = reclamacion.getFecha();
		String lugarServ = reclamacion.getServicio().getLugar();
		String nombreCli = reclamacion.getCliente().getNombre();

		if (nombreCli == null || nombreCli.trim().equals("")) {
			errors.rejectValue("nombreCli", REQUIRED, REQUIRED);
		}
		
		if (lugarServ == null || lugarServ.trim().equals("")) {
			errors.rejectValue("lugarServ", REQUIRED, REQUIRED);
		}
		
		if (fecha == null || !Pattern.matches("yyyy/MM/dd", fecha.format(DateTimeFormatter.ofPattern("yyyy/MM/dd")))) {
			errors.rejectValue("fecha", REQUIRED + " debe tener 4 dígitos el año, 2 dígitos el mes, 2 dígitos el día y estar separados por barra", REQUIRED + " debe tener 4 dígitos el año, 2 dígitos el mes, 2 dígitos el día y estar separados por barra");
		}

	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Reclamacion.class.isAssignableFrom(clazz);
	}

}
