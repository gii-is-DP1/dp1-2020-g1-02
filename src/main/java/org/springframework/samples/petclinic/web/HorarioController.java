package org.springframework.samples.petclinic.web;

import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Horario;
import org.springframework.samples.petclinic.model.Servicio;
import org.springframework.samples.petclinic.model.Trabajador;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.service.HorarioService;
import org.springframework.samples.petclinic.service.TrabajadorService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.samples.petclinic.service.exceptions.HorarioException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/horarios")
public class HorarioController {
	
	@Autowired
	private HorarioService horarioService;
	
	@Autowired
	private TrabajadorService trabajadorService;
	@Autowired
	private UserService userService;
	
	@GetMapping()
	public String listadoHorarios(ModelMap modelMap) {
		String vista ="horarios/listadoHorarios";
		Iterable<Horario> horarios = horarioService.findAll();
		modelMap.addAttribute("horarios", horarios);
		return vista;
	}
	
	@GetMapping(value = "/misHorarios")
	public String listadoHorariosPorTrabajadorUsername(ModelMap modelMap) {
		String vista = "horarios/listadoHorariosPorTrabajador";
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Trabajador trabajador = trabajadorService.findTrabajadorByUsername(auth.getName()).get();
		Iterable<Horario> horarios = horarioService.findHorarioByTrabajadorId(trabajador.getId());
		modelMap.addAttribute("horarios", horarios);
		return vista;
	}
	
	
	@GetMapping(path="/new")
	public String crearHorario(ModelMap modelMap) {
		String view="horarios/newHorario";
		Iterable<Trabajador> trabajadores = trabajadorService.findAll();
		modelMap.addAttribute("trabajadores", trabajadores);
		modelMap.addAttribute("horario", new Horario());
		return view;
	}
	
	@PostMapping(path="/save")
	public String salvarHorario(@Valid Horario horario, BindingResult result,ModelMap modelMap) {
		String view="redirect:/horarios";
		if(result.hasErrors()) {
			modelMap.addAttribute("horario", horario);
			return "horarios/newHorario";
		}else {
			try {
				Trabajador trabajador = horario.getTrabajador();
				horarioService.crearHorario(horario, trabajador);
				return view;
			} catch (HorarioException e) {
				modelMap.addAttribute("horario", horario);
				modelMap.addAttribute("error", "Las horas se solapan");
				return  "horarios/newHorario";
			}
		}
	}
	
	@GetMapping(path="/delete/{horarioId}")
	public String borrarHorario(@PathVariable("horarioId") Integer horarioId, ModelMap modelmap) {
		String view = "redirect:/horarios";
		Optional<Horario> horario = horarioService.findHorarioById(horarioId);
		if(horario.isPresent()) {
			horarioService.delete(horario.get());
			modelmap.addAttribute("message", "Horario borrada correctamente");
		}else {
			modelmap.addAttribute("message", "Horario no encontrada");
		}
		return view;
	}

}
