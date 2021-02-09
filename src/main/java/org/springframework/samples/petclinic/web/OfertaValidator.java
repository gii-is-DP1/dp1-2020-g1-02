package org.springframework.samples.petclinic.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Oferta;
import org.springframework.samples.petclinic.service.OfertaService;
import org.springframework.samples.petclinic.service.ProveedorService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class OfertaValidator implements Validator {
	
private static final String REQUIRED = "required";
	
	@Autowired
	private OfertaService ofertaService;
	@Autowired
	private ProveedorService provService;
	
	
	public Boolean compruebaProveedor(Integer proveedorId) {
		if(provService.findProveedorById(proveedorId).isPresent()) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Oferta oferta = (Oferta) obj;
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Oferta.class.isAssignableFrom(clazz);
	}

}
