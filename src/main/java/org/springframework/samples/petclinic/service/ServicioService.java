package org.springframework.samples.petclinic.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.EstadoServicio;
import org.springframework.samples.petclinic.model.Mensaje;
import org.springframework.samples.petclinic.model.Servicio;
import org.springframework.samples.petclinic.model.Trabajador;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.repository.ServicioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServicioService {
	
	@Autowired
	private ServicioRepository servicioRepo;

	@Autowired
	private TrabajadorService trabajadorService;
	
	@Autowired
	private MensajesService mensajesService;
	
	@Autowired
	private UserService	userService;

	
	@Transactional
	public int servicioCount() {
		return (int) servicioRepo.count();
	}
	
	@Transactional(readOnly=true)
	public Iterable<Servicio> findAll() {
		return servicioRepo.findAll();
	}
	
	@Transactional
	public void save(Servicio servicio) {
		servicioRepo.save(servicio);
	}
	
	
	public void vaciarTrabajadores(Servicio servicio) {
		List<Trabajador> trabajadores = (List<Trabajador>) trabajadorService.findTrabajadoresByServicio(servicio.getId());
		for(Trabajador t : trabajadores) {
			t.getServicios().remove(servicio);
		}
	}
	@Transactional
	public void asignarTrabajadores(Servicio servicio) {
		servicioRepo.save(servicio);
		List<Trabajador> ls=servicio.getTrabajadores();
		for(Trabajador t: ls) {
			t.getServicios().add(servicio);
			trabajadorService.save(t);
		}
	}
	
	@Transactional
	public void delete(Servicio servicio) {
		vaciarTrabajadores(servicio);
		servicioRepo.delete(servicio);
	}
	
	@Transactional(readOnly=true)
	public Optional<Servicio> findServicioById(int sId) {
		return servicioRepo.findById(sId);
	}

	@Transactional
	public void aceptar(Servicio servicio) {
		servicio.setEstado(EstadoServicio.Aceptado);
		servicioRepo.save(servicio);
		Mensaje m= new Mensaje();
		m.setEmisor(userService.findUser("Sistema").get());
		List<User> l=new ArrayList<>();
		l.add(servicio.getCliente().getUser());
		m.setLeido(false);
		m.setReceptores(l);
		m.setFecha(LocalDate.now());
		m.setAsunto("Servicio: " + servicio.getLugar());
		m.setCuerpo("Gracias por su solicitud de Servicio, El administrar " + userService.getLoggedUser().getUsername() + " ha aceptado el servicio."
				+ "Procedemos a enviarle un presupuesto para que pueda estudiarlo, este podras aceptarlo o rechazarlo desde la misma aplicacion");
		mensajesService.save(m);
		
	}
	
	@Transactional
	public void rechazar(Servicio servicio) {
		servicio.setEstado(EstadoServicio.Rechazado);
		servicioRepo.save(servicio);
		Mensaje m= new Mensaje();
		m.setEmisor(userService.findUser("Sistema").get());
		List<User> l=new ArrayList<>();
		l.add(servicio.getCliente().getUser());
		m.setLeido(false);
		m.setReceptores(l);
		m.setFecha(LocalDate.now());
		m.setAsunto("Servicio: " + servicio.getLugar());
		m.setCuerpo("Gracias por su solicitud de Servicio, lo sentimos pero hemos decidido rechazar su propuesta");
		mensajesService.save(m);
	}
  
	@Transactional(readOnly=true)
	public Iterable<Servicio> serviciosByCliente(Integer id){
		return servicioRepo.serviciosByCliente(id);
	}
	
	@Transactional(readOnly=true)
	public Iterable<Servicio> findServiciosByTrabajador(Integer trabajador) {
		return servicioRepo.serviciosByTrabajador(trabajador);
	}
}
