package org.springframework.samples.petclinic.web;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DissanErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(ModelMap modelMap) {
    	modelMap.addAttribute("hola", "No tiene permiso para acceder aquí.");
        return "exception";
    }
	
	@Override
	public String getErrorPath() {
		return "/error";
	}

}
