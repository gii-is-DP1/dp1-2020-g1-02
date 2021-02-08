package org.springframework.samples.petclinic.web;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DissanErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError() {
        return "prohibido";
    }
	
	@Override
	public String getErrorPath() {
		return "/error";
	}

}
