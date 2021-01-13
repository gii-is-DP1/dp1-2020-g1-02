package org.springframework.samples.petclinic.web;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Servicio;
import org.springframework.samples.petclinic.service.ServicioService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ServicioValidator implements Validator {
	
private static final String REQUIRED = "required";
	
	@Autowired
	private ServicioService servicioService;
	
	
	public Boolean servicioDeUnMismoCliente(String nombre) {
		nombre = nombre.toLowerCase();
		Boolean result = false;
		Iterator<Servicio> itServicio = this.servicioService.findAll().iterator();
		while(itServicio.hasNext()) {
			Servicio servicio = itServicio.next();
			String nombreCli = servicio.getCliente().getNombre().toLowerCase();
			if(nombreCli.equals(nombre)) {
				result = true;
			}
		}
		return result;
	}
	
	public Boolean reclamacionDeUnMismoContrato(String nombre) {
		nombre = nombre.toLowerCase();
		Boolean result = false;
		Iterator<Servicio> itServicio = this.servicioService.findAll().iterator();
		while(itServicio.hasNext()) {
			Servicio servicio = itServicio.next();
			String nombreCliCont = servicio.getContrato().getCliente().getNombre().toLowerCase();
			if(nombreCliCont.equals(nombre)) {
				result = true;
			}
		}
		return result;
	}
	
	@Override
	public void validate(Object obj, Errors errors) {
		Servicio servicio = (Servicio) obj;
		LocalDate fechainicio = servicio.getFechainicio();
		LocalDate fechafin = servicio.getFechafin();
		String lugarServ = servicio.getLugar();

		if (lugarServ == null || lugarServ.trim().equals("")) {
			errors.rejectValue("lugarServ", REQUIRED, REQUIRED);
		}
		
		if (fechainicio == null || !Pattern.matches("yyyy/MM/dd", fechainicio.format(DateTimeFormatter.ofPattern("yyyy/MM/dd")))) {
			errors.rejectValue("fechainicio", REQUIRED + " debe tener 4 dígitos el año, 2 dígitos el mes, 2 dígitos el día y estar separados por barra", REQUIRED + " debe tener 4 dígitos el año, 2 dígitos el mes, 2 dígitos el día y estar separados por barra");
		}

		if (fechafin == null || !Pattern.matches("yyyy/MM/dd", fechafin.format(DateTimeFormatter.ofPattern("yyyy/MM/dd")))) {
			errors.rejectValue("fechafin", REQUIRED + " debe tener 4 dígitos el año, 2 dígitos el mes, 2 dígitos el día y estar separados por barra", REQUIRED + " debe tener 4 dígitos el año, 2 dígitos el mes, 2 dígitos el día y estar separados por barra");
		}

	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Servicio.class.isAssignableFrom(clazz);
	}

}
