package org.springframework.samples.petclinic.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.EstadoServicio;
import org.springframework.samples.petclinic.model.Mensaje;
import org.springframework.samples.petclinic.model.Servicio;
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
	
	public Iterable<Servicio> findAll() {
		return servicioRepo.findAll();
	}
	
	@Transactional
	public void save(Servicio servicio) {
		servicioRepo.save(servicio);
	}
	
	@Transactional
	public void delete(Servicio servicio) {
		servicioRepo.delete(servicio);
	}
	
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
	
//	public boolean cumpleNoEsLaMismaHora(Servicio servicio, Trabajador trabajador) {
//		Boolean res = true;
//		Set<Horario> horarios = trabajador.getHorarios();
//		Iterator<Horario> iterador = horarios.iterator();
//		while(iterador.hasNext()) {
//			Horario h1 = iterador.next();
//			Horario h2 = iterador.next();
//			LocalDateTime diaconhora1 = h1.getHora_inicio();
//			LocalDateTime diaconhora2 = h2.getHora_inicio();
//			if(diaconhora1 == diaconhora2) {
//				res = false;
//			}
//		}
//		return res;
//	}
//	
//	public void crearServicio(Servicio servicio, Trabajador trabajador) throws HorarioServicioException {
//		if (this.cumpleNoEsLaMismaHora(servicio,trabajador)) {
//			this.save(servicio);
//		}else {
//			throw new HorarioServicioException();
//		}
//	}
	
	public Iterable<Servicio> serviciosByCliente(Integer id){
		return servicioRepo.serviciosByCliente(id);
	}
}
