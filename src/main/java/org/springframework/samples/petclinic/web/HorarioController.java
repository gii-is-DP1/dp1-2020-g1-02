package org.springframework.samples.petclinic.web;

import java.util.Optional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Horario;
import org.springframework.samples.petclinic.model.Trabajador;
import org.springframework.samples.petclinic.service.HorarioService;
import org.springframework.samples.petclinic.service.TrabajadorService;
import org.springframework.samples.petclinic.service.exceptions.SolapamientoFechasException;
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
	
	@GetMapping(value="/{trabajadorId}")
	public String horariosByTrabajador(@PathVariable("trabajadorId") Integer trabajadorId, ModelMap modelMap) {
		String vista = "horarios/listadoHorariosPorTrabajador";
		Iterable<Horario> horarios = horarioService.findHorarioByTrabajadorId(trabajadorId);
		Trabajador t=trabajadorService.findTrabajadorById(trabajadorId).get();
		String trabajador=t.getNombre() + " " + t.getApellidos();
		modelMap.addAttribute("horarios", horarios);
		modelMap.addAttribute("trabajador", trabajador);
		return vista;
	}
	
	
	@GetMapping(path="/new/{trabajadorId}")
	public String crearHorario(@PathVariable("trabajadorId") Integer trabajadorId,ModelMap modelMap) {
		String view="horarios/newHorario";
		modelMap.addAttribute("trabajador",trabajadorId);
		modelMap.addAttribute("horario", new Horario());
		return view;
	}
	
	@PostMapping(path="/save")
	public String salvarHorario(@Valid Horario horario, BindingResult result,ModelMap modelMap) {
		String view="redirect:/horarios/" + horario.getTrabajador().getId();
		if(result.hasErrors()) {
			modelMap.addAttribute("horario", horario);
			modelMap.addAttribute("trabajador", horario.getTrabajador().getId());
			modelMap.addAttribute("message", "La hora de inicio tiene que ser antes que la hora de fin");
			return  "horarios/newHorario";
		}else {
			try {
				horarioService.crearHorario(horario);
				return view;
			} catch (SolapamientoFechasException e) {
				modelMap.addAttribute("horario", horario);
				modelMap.addAttribute("message", "Las horas se solapan");
				return  "redirect:/horarios/new/" + horario.getTrabajador().getId();
//			} catch(HoraNoAdecuadaException e) {
//				modelMap.addAttribute("horario", horario);
//				modelMap.addAttribute("message", "Tiene que ser en punto o y media");
//				return  "horarios/newHorario";
//			}
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
