package org.springframework.samples.petclinic.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.TipoCategoria;
import org.springframework.samples.petclinic.service.ValoracionService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {
	
		@Autowired
		private ValoracionService valoracionService;
	
	  @GetMapping({"/","/welcome"})
	  public String welcome(Map<String, Object> model) {
		  Map<TipoCategoria, Integer> valoraciones = valoracionService.getMediaValoraciones();
		 model.put("valoraciones", valoraciones);
		  
	    return "welcome";
	  }
}
