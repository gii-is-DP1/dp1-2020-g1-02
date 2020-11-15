package org.springframework.samples.petclinic.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Event;
import org.springframework.samples.petclinic.service.EventService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/events")
public class EventController {

	@Autowired
	private EventService eventService;
	
	@GetMapping()
	public String listadoEventos(ModelMap modelMap) {
		String vista ="events/listadoEventos";
		Iterable<Event> events = eventService.findAll();
		modelMap.addAttribute("events", events);
		return vista;
	}
}
