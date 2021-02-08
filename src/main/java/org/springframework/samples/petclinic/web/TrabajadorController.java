package org.springframework.samples.petclinic.web;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Servicio;
import org.springframework.samples.petclinic.model.Trabajador;
import org.springframework.samples.petclinic.service.HorarioService;
import org.springframework.samples.petclinic.service.InstalacionService;
import org.springframework.samples.petclinic.service.RegistroHorasService;
import org.springframework.samples.petclinic.service.ServicioService;
import org.springframework.samples.petclinic.service.TrabajadorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/trabajadores")
public class TrabajadorController {

	@Autowired
	private TrabajadorService trabajadorService;
	@Autowired
	private RegistroHorasService registroHorasService;
	@Autowired
	private HorarioService horarioService;
	@Autowired
	private InstalacionService instalacionService;
	@Autowired
	private ServicioService servicioService;
	
	@GetMapping()
	public String listadoTrabajadores(ModelMap modelMap) {
		String vista ="trabajadores/listadoTrabajadores";
		Iterable<Trabajador> trabajadores = trabajadorService.findAll();
		modelMap.addAttribute("trabajadores", trabajadores);
		return vista;
	}
	
	@GetMapping(path="/new")
	public String crearTrabajador(ModelMap modelMap) {
		String view="trabajadores/editTrabajadores";
		modelMap.addAttribute("trabajador", new Trabajador());
		return view;
	}
	
	@PostMapping(path="/save")
	public String salvarTrabajador(@Valid Trabajador trabajador, BindingResult result,ModelMap modelMap) {
		String view="redirect:/trabajadores";
		if(result.hasErrors()) {
			modelMap.addAttribute("trabajador", trabajador);
			return "trabajadores/editTrabajadores";
		}else {
			trabajadorService.save(trabajador);
			modelMap.addAttribute("message", "Trabajador actualizado!");
		}
		return view;
	}
	
	@GetMapping(value = "/{trabajadorId}/edit")
	public String iniciarFormularioActualizacionTrabajador(@PathVariable("trabajadorId") int trabajadorId, ModelMap modelMap) {
		Trabajador trabajador = this.trabajadorService.findTrabajadorById(trabajadorId).get();
		modelMap.addAttribute(trabajador);
		return "trabajadores/editTrabajadores";
	}
	
	@PostMapping(path="/{trabajadorId}/update")
	public String updateTrabajador(@PathVariable("trabajadorId") int trabajadorId, @Valid Trabajador trabajador, BindingResult result,ModelMap modelMap) {
		String view="redirect:/trabajadores";
		if(result.hasErrors()) {
			modelMap.addAttribute("trabajador", trabajador);
			return "trabajadores/editTrabajadores";
		}else {
			Trabajador trabajadorToUpdate = trabajadorService.findTrabajadorById(trabajadorId).get();
			trabajadorToUpdate.setNombre(trabajador.getNombre());
			trabajadorToUpdate.setApellidos(trabajador.getApellidos());
			trabajadorToUpdate.setDni(trabajador.getDni());
			trabajadorToUpdate.setCorreo(trabajador.getCorreo());
			trabajadorToUpdate.setDireccion(trabajador.getDireccion());
			trabajadorToUpdate.setTelefono(trabajador.getTelefono());
			trabajadorToUpdate.setTipocategoria(trabajador.getTipocategoria());
			trabajadorService.save(trabajadorToUpdate);
			modelMap.addAttribute("message", "Trabajador actualizado!");
		}
		return view;
	}
	
	@GetMapping(path="/delete/{trabajadorId}")
	public String borrarTrabajador(@PathVariable("trabajadorId") int trabajadorId, ModelMap modelmap) {
		String view="redirect:/trabajadores";
		Optional<Trabajador> trabajador=trabajadorService.findTrabajadorById(trabajadorId);
		if(trabajador.isPresent()) {
			trabajadorService.delete(trabajador.get());
			modelmap.addAttribute("message", "Trabajador borrado correctamente");
		}else {
			modelmap.addAttribute("message", "Trabajador no encontrado");
			view=listadoTrabajadores(modelmap);
		}
		return view;
	}
	
//	@GetMapping(path="/registroHoras/{nameTrabajador}")
//	public String filtradoFactura(@PathVariable("nameTrabajador") String nameTrabajador,ModelMap modelMap) {
//		String view="redirect:/registroHoras";
//		modelMap.addAttribute("registroHoras", registroHorasService.findRegistroHorasByTrabajadorId(nameTrabajador));
//		return view;
//	}
	
//	@GetMapping(path="/{trabajadorId}")
//	public String listadoHorariosDeUnTrabajador(ModelMap modelMap, Trabajador trabajador) {
//		String vista ="trabajadores/listadoJornadaLaboral";
//		Iterable<Horario> horarios = horarioService.findHorarioByTrabajadorName(trabajador.getNombre());
//		modelMap.addAttribute("horario", horarios);
//		return vista;
//	}
	
	@GetMapping(path="/horariosTrabajador")
	public String horariosTrabajador(String nombreTrab, ModelMap modelMap) {
		String view="horarios/listadoHorarios";
		modelMap.addAttribute("horariosTrabajador", nombreTrab);
		modelMap.addAttribute("horarios", horarioService.findHorarioByTrabajadorName(nombreTrab));
		return view;
	}
	
	@GetMapping(path="/instalacionesCliente")
	public String instalacionesCliente(ModelMap modelMap, String nombreCli) {
		String view="trabajadores/listadoTrabajadores";
		modelMap.addAttribute("instalacionesCliente", nombreCli);
		modelMap.addAttribute("instalaciones", instalacionService.findInstalacionByClienteName(nombreCli));
		return view;
	}
	
	@GetMapping(path="/servicio/{servicioId}")
	public String trabajdoresByServicio(@PathVariable("servicioId") Integer servicioId, ModelMap modelMap) {
		String view="trabajadores/trabajadoresByServicio";
		Servicio s= servicioService.findServicioById(servicioId).get();
		modelMap.addAttribute("trabajadores", trabajadorService.findTrabajadoresByServicio(servicioId));
		modelMap.addAttribute("servicio", s.getLugar());
		return view;
	}
}
