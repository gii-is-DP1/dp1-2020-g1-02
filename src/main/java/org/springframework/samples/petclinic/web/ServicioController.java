package org.springframework.samples.petclinic.web;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Presupuesto;
import org.springframework.samples.petclinic.model.Servicio;
import org.springframework.samples.petclinic.model.Trabajador;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.samples.petclinic.service.PresupuestoService;
import org.springframework.samples.petclinic.service.ServicioService;
import org.springframework.samples.petclinic.service.TrabajadorService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.samples.petclinic.service.exceptions.PresupuestoYaAceptadoException;
import org.springframework.samples.petclinic.service.exceptions.ServicioNoAceptadoException;
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
@RequestMapping("/servicios")
public class ServicioController {
	
	@Autowired
	private ServicioService servicioService;
	@Autowired
	private ClienteService clienteService;
	@Autowired
	private PresupuestoService presupuestoService;
	@Autowired
	private UserService userService;
	@Autowired
	private TrabajadorService trabajadorService;
	
	@GetMapping()
	public String listadoServicios(ModelMap modelMap) {
		String vista = "servicios/listadoServicios";
		Iterable<Servicio> servicios = servicioService.findAll();
		modelMap.addAttribute("servicios", servicios);
		return vista;
	}
	
	@GetMapping(path="/delete/{servicioId}")
	public String borrarServicio(@PathVariable("servicioId") int servicioId, ModelMap modelmap) {
		String view="redirect:/servicios";
		 Optional<Servicio> s=servicioService.findServicioById(servicioId);
		if(s.isPresent()) {
			servicioService.delete(s.get());
			modelmap.addAttribute("message", "Servicio borrado correctamente");
		}else {
			modelmap.addAttribute("message", "Servicio no encontrado");
		}
		return view;
	}
	
	@GetMapping(path="/edit")
	public String editaServicio(ModelMap modelMap) {
		String view="servicios/editServicio";
		modelMap.addAttribute("servicio", new Servicio());
		return view;
	}
	
	@GetMapping(path="/new")
	public String crearServicio(ModelMap modelMap) {
		String view="servicios/editServicio";
		modelMap.addAttribute("servicio", new Servicio());
		return view;
	}
	
	@PostMapping(path="/save")
	public String salvarServicio(@Valid Servicio servicio, BindingResult result, ModelMap modelMap) {
		String view="redirect:/servicios/misServicios";
		if(result.hasErrors()) {
			modelMap.addAttribute("servicio", servicio);
			return "servicios/editServicio";
		}
		else {
			User user =  userService.getLoggedUser();
			Cliente cliente=clienteService.findClienteByUsername(user.getUsername()).get();
			servicio.setCliente(cliente);
			
			servicioService.save(servicio);
			modelMap.addAttribute("message", "Servicio actualizado!");
		}
		return view;
	}
	
	@PostMapping(path="/aceptar")
	public String aceptarServicio(Integer id, ModelMap modelMap) {
		String view="redirect:/servicios/" + id + "/presupuestos/new";
		Optional<Servicio> s= servicioService.findServicioById(id);
		servicioService.aceptar(s.get());
		return view;
	}
	
	@PostMapping(path="/rechazar")
	public String rechazarServicio(Integer id, ModelMap modelMap) {
		String view="redirect:/servicios";
		Optional<Servicio> s= servicioService.findServicioById(id);
		servicioService.rechazar(s.get());
		return view;
	}
	
	@GetMapping(value = "/{clienteId}")
	public String listadoServiciosPorClienteId(@PathVariable("clienteId") int clienteId, ModelMap modelMap) {
		String vista = "servicios/listadoServiciosPorCliente";
		Iterable<Servicio> servicios = servicioService.serviciosByCliente(clienteId);
		modelMap.addAttribute("servicios", servicios);
		return vista;
	}
	
	@GetMapping(value = "/misServicios")
	public String listadoServiciosPorClienteUsername(ModelMap modelMap) {
		String vista = "servicios/listadoServiciosPorCliente";
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Cliente client = clienteService.findClienteByUsername(auth.getName()).get();
		Iterable<Servicio> servicios = servicioService.serviciosByCliente(client.getId());
		modelMap.addAttribute("servicios", servicios);
		return vista;
	}
	
	@GetMapping(value="/{servicioId}/presupuestos")
	public String listadoPresupuestos(@PathVariable("servicioId") Integer id, ModelMap modelMap) {
		String vista = "presupuestos/listadoPresupuestoPorServicio";
		Iterable<Presupuesto> presupuestos=presupuestoService.presupuestosByServicio(id);
		modelMap.addAttribute("presupuestos", presupuestos);
		return vista;
	}
	
	@GetMapping(path="/{servicioId}/presupuestos/new")
	public String crearPresupuesto(@PathVariable("servicioId") Integer servicioId, ModelMap modelMap) {
		String view = "presupuestos/editPresupuesto";
		Presupuesto p= new Presupuesto();
		p.setServicio(servicioService.findServicioById(servicioId).get());
		modelMap.addAttribute("presupuesto", p);
		return view;
	}
	
	@PostMapping(path="/presupuestos/save")
	public String salvarPresupuesto(@Valid Presupuesto presupuesto, BindingResult result, ModelMap modelMap) {
		String view = "redirect:/servicios/"  + presupuesto.getServicio().getId() + "/presupuestos";
		if(result.hasErrors()) {
			modelMap.addAttribute("presupuesto", presupuesto);
			return "presupuestos/editPresupuesto";
		}else {
			try {
				presupuestoService.comprobarExcepciones(presupuesto);
				modelMap.addAttribute("message", "Presupuesto actualizado!!");
			} catch(PresupuestoYaAceptadoException e) {
				modelMap.addAttribute("message", "No se puede enviar un presupuesto a un servicio que ya tiene un presupuesto aceptado");
				view="/exception";
			} catch(ServicioNoAceptadoException e) {
				modelMap.addAttribute("message", "No se puede enviar un presupuesto a un servicio que no está aceptado");
				view="/exception";
			}
		}
		return view;
	}
	
	@PostMapping(path="/{presupuestoId}/aceptar")
	public String aceptarPresupuesto(Integer id, ModelMap modelMap) {
		Optional<Presupuesto> p= presupuestoService.findPresupuestoById(id);
		presupuestoService.aceptar(p.get());
		String view="redirect:/servicios/" + p.get().getServicio().getId() + "/presupuestos";
		return view;
	}
	
	@PostMapping(path="/{presupuestoId}/rechazar")
	public String rechazarPresupuesto(Integer id, ModelMap modelMap) {
		Optional<Presupuesto> p= presupuestoService.findPresupuestoById(id);
		presupuestoService.rechazar(p.get());;
		String view="redirect:/servicios/" + p.get().getServicio().getId() + "/presupuestos";
		return view;
	}
	
	@GetMapping(path="/trabajadores/{trabajadorId}")
	public String trabajdoresByServicio(@PathVariable("trabajadorId") Integer trabajadorId, ModelMap modelMap) {
		String view="servicios/serviciosByTrabajador";
		Trabajador t= trabajadorService.findTrabajadorById(trabajadorId).get();
		modelMap.addAttribute("servicios", servicioService.findServiciosByTrabajador(trabajadorId));
		modelMap.addAttribute("trabajador",t);
		return view;
	}
}
