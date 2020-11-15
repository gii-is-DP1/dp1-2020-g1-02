package org.springframework.samples.petclinic.web;

import java.util.Optional;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Trabajador;
import org.springframework.samples.petclinic.service.TrabajadorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/events")
public class EventController {

	@Autowired
	private TrabajadorService eventService;
	
	@GetMapping()
	public String listadoEventos(ModelMap modelMap) {
		String vista ="events/listadoEventos";
		Iterable<Trabajador> events = eventService.findAll();
		modelMap.addAttribute("events", events);
		return vista;
	}
	
	@GetMapping(path="/new")
	public String crearEvento(ModelMap modelMap) {
		String view="events/editEvent";
		modelMap.addAttribute("event", new Trabajador());
		return view;
	}
	
	@PostMapping(path="/save")
	public String salvarEvento(@Valid Trabajador event, BindingResult result,ModelMap modelMap) {
		String view="events/listadoEventos";
		if(result.hasErrors()) {
			modelMap.addAttribute("event", event);
			return "events/editEvent";
		}else {
			eventService.save(event);
			modelMap.addAttribute("message", "Evento actualizado!");
			view=listadoEventos(modelMap);
		}
		return view;
	}
	
	@GetMapping(path="/delete/{eventId}")
	public String borrarEvento(@PathVariable("eventId") int eventId, ModelMap modelmap) {
		String view="events/listadoEventos";
		Optional<Trabajador> event=eventService.findEventById(eventId);
		if(event.isPresent()) {
			eventService.delete(event.get());
			modelmap.addAttribute("message", "Trabajador borrado correctamente");
		}else {
			modelmap.addAttribute("message", "Trabajador on encontrado");
			view=listadoEventos(modelmap);
		}
		return view;
	}
}
