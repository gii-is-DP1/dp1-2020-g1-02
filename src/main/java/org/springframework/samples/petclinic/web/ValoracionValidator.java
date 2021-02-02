package org.springframework.samples.petclinic.web;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.NivelSatisfaccion;
import org.springframework.samples.petclinic.model.Valoracion;
import org.springframework.samples.petclinic.service.ValoracionService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ValoracionValidator implements Validator {

private static final String REQUIRED = "required";
	
	@Autowired
	private ValoracionService valoracionService;
	
	
	public Boolean valoracionDeUnMismoCliente(String nombre) {
		nombre = nombre.toLowerCase();
		Boolean result = false;
		Iterator<Valoracion> itValoracion = this.valoracionService.findAll().iterator();
		while(itValoracion.hasNext()) {
			Valoracion valoracion = itValoracion.next();
			String nombreCli = valoracion.getCliente().getNombre().toLowerCase();
			if(nombreCli.equals(nombre)) {
				result = true;
			}
		}
		return result;
	}
	
	public Boolean valoracionDeUnMismoServicio(String lugar) {
		lugar = lugar.toLowerCase();
		Boolean result = false;
		Iterator<Valoracion> itValoracion = this.valoracionService.findAll().iterator();
		while(itValoracion.hasNext()) {
			Valoracion valoracion = itValoracion.next();
			String nombreServ = valoracion.getServicio().getLugar().toLowerCase();
			if(nombreServ.equals(lugar)) {
				result = true;
			}
		}
		return result;
	}
	
	@Override
	public void validate(Object obj, Errors errors) {
		Valoracion valoracion = (Valoracion) obj;
		String nombreCli = valoracion.getCliente().getNombre();
		String lugarServ = valoracion.getServicio().getLugar();
		LocalDate fecha = valoracion.getFecha();
		NivelSatisfaccion nivelSas = valoracion.getNivelsatisfaccion();
		

		if (lugarServ == null || lugarServ.trim().equals("")) {
			errors.rejectValue("lugarServ", REQUIRED, REQUIRED);
		}
		
		if (nombreCli == null || nombreCli.trim().equals("")) {
			errors.rejectValue("nombreCli", REQUIRED, REQUIRED);
		}
		
		if (fecha == null || !Pattern.matches("yyyy/MM/dd", fecha.format(DateTimeFormatter.ofPattern("yyyy/MM/dd")))) {
			errors.rejectValue("fecha", REQUIRED + " debe tener 4 dígitos el año, 2 dígitos el mes, 2 dígitos el día y estar separados por barra", REQUIRED + " debe tener 4 dígitos el año, 2 dígitos el mes, 2 dígitos el día y estar separados por barra");
		}

		if (nivelSas == null || NivelSatisfaccion.Alto!=nivelSas || NivelSatisfaccion.Bajo!=nivelSas || NivelSatisfaccion.Medio!=nivelSas || NivelSatisfaccion.MuyAlto!=nivelSas || NivelSatisfaccion.MuyBajo!=nivelSas) {
			errors.rejectValue("nivelSas", REQUIRED + " debe ser alto, bajo, medio, muy alto o muy bajo", REQUIRED + " debe ser alto, bajo, medio, muy alto o muy bajo");
		}

	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Valoracion.class.isAssignableFrom(clazz);
	}
}
