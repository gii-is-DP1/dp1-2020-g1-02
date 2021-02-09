package org.springframework.samples.petclinic.web;

import java.util.Iterator;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ClienteValidator implements Validator {
	
	private static final String REQUIRED = "required";
	
	@Autowired
	private ClienteService clienteService;
	
	public Boolean clienteConMismoDNI(String dni) {
		dni = dni.toLowerCase();
		Boolean result = false;
		Iterator<Cliente> itClientes = this.clienteService.findAll().iterator();
		while(itClientes.hasNext()) {
			Cliente cliente = itClientes.next();
			String pruebaDni = cliente.getDni().toLowerCase();
			if(pruebaDni.equals(dni)) {
				result = true;
			}
		}
		return result;
	}
	
	@Override
	public void validate(Object obj, Errors errors) {
		Cliente cliente = (Cliente) obj;
		String name = cliente.getNombre();
		String dni = cliente.getDni();
		String telefono = cliente.getTelefono();

		if (name == null || name.trim().equals("")) {
			errors.rejectValue("name", REQUIRED, REQUIRED);
		}

		if (dni == null || dni.trim().equals("") || !Pattern.matches("[0-9]{8}[A-Z]{1}", dni)) {
			errors.rejectValue("dni", REQUIRED + " debe tener 8 digitos y un caracter en mayúscula al final", REQUIRED + " debe tener 8 digitos y un caracter en mayúscula al final");
		}

		if (telefono == null || telefono.trim().equals("") || !Pattern.matches("[0-9]{9}", telefono)) {
			errors.rejectValue("telefono", REQUIRED + " debe tener 9 digitos", REQUIRED + " debe tener 9 digitos");
		}
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Cliente.class.isAssignableFrom(clazz);
	}

}
