package org.springframework.samples.petclinic.web;

import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Proveedor;
import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.samples.petclinic.service.ProveedorService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ProveedorValidator implements Validator {
	
	private static final String REQUIRED = "required";
	
	@Autowired
	private ProveedorService provService;
	
	
	public Boolean proveedorConMismoNombre(String name) {
		name = name.toLowerCase();
		Boolean result = false;
		Iterator<Proveedor> itProv = this.provService.findAll().iterator();
		while(itProv.hasNext()) {
			Proveedor prov = itProv.next();
			String pruebaName = prov.getName().toLowerCase();
			if(pruebaName.equals(name)) {
				result = true;
			}
		}
		return result;
	}
	
	@Override
	public void validate(Object obj, Errors errors) {
		Proveedor prov = (Proveedor) obj;
		String name = prov.getName();
		String direccion = prov.getDireccion();
		String telefono = prov.getTelefono();

		if (name == null || name.trim().equals("")) {
			errors.rejectValue("name", REQUIRED, REQUIRED);
		}

		if (direccion == null || direccion.trim().equals("")) {
			errors.rejectValue("direccion", REQUIRED , REQUIRED );
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
