package org.springframework.samples.petclinic.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.EstadoServicio;
import org.springframework.samples.petclinic.model.Mensaje;
import org.springframework.samples.petclinic.model.Presupuesto;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.repository.PresupuestoRepository;
import org.springframework.samples.petclinic.service.exceptions.LimitePedidoException;
import org.springframework.samples.petclinic.service.exceptions.PresupuestoYaAceptadoException;
import org.springframework.samples.petclinic.service.exceptions.ServicioNoAceptadoException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PresupuestoService {
	
	@Autowired
	private PresupuestoRepository presupuestoRepo;
	
	@Autowired
	private MensajesService mensajesService;
	
	@Autowired
	private UserService	userService;

	@Transactional
	public int presupuestoCount() {
		return (int) presupuestoRepo.count();
	}
	
	public Iterable<Presupuesto> findAll() {
		return presupuestoRepo.findAll();
	}
	
	@Transactional
	public void save(Presupuesto presupuesto) {
		presupuestoRepo.save(presupuesto);
	}
	
	@Transactional
	public void delete(Presupuesto presupuesto) {
		presupuestoRepo.delete(presupuesto);
	}
	
	public Optional<Presupuesto> findPresupuestoById(int pId) {
		return presupuestoRepo.findById(pId);
	}
	
	public Iterable<Presupuesto>presupuestosByServicio(Integer id){
		return presupuestoRepo.presupuestosByServicio(id);
	}

	@Transactional
	public void aceptar(Presupuesto presupuesto) {
		presupuesto.setEstado(EstadoServicio.Aceptado);
		presupuestoRepo.save(presupuesto);
		
		Mensaje m= new Mensaje();
		m.setEmisor(userService.findUser("Sistema").get());
		
		List<User> l=new ArrayList<>();
		l.add(userService.findUser("admin").get());;
		m.setReceptores(l);
		
		m.setLeido(false);
		m.setFecha(LocalDate.now());
		m.setAsunto("Presupuesto del servicio: " + presupuesto.getServicio().getLugar());
		m.setCuerpo("El cliente " + userService.getLoggedUser().getUsername() + " ha aceptado el presupuesto.");
		mensajesService.save(m);
	}
	
	@Transactional
	public void rechazar(Presupuesto presupuesto) {
		presupuesto.setEstado(EstadoServicio.Rechazado);
		presupuestoRepo.save(presupuesto);
		
		Mensaje m= new Mensaje();
		m.setEmisor(userService.findUser("Sistema").get());
		
		List<User> l=new ArrayList<>();
		l.add(userService.findUser("admin").get());
		m.setReceptores(l);
		
		m.setLeido(false);
		m.setFecha(LocalDate.now());
		m.setAsunto("Presupuesto del servicio: " + presupuesto.getServicio().getLugar());
		m.setCuerpo("El cliente " + userService.getLoggedUser().getUsername() + " ha rechazado el presupuesto.");
		mensajesService.save(m);
	}
	
	
	public Integer numeroPresupuestosByServicioConEstadoAceptado(Integer id) {
		return presupuestoRepo.numeroPresupuestosByServicioConEstadoAceptado(id);
	}
	
	@Transactional(rollbackFor = PresupuestoYaAceptadoException.class)
	public void presupuestoYaAceptado(Presupuesto presupuesto) throws PresupuestoYaAceptadoException {
		Integer a=presupuestoRepo.numeroPresupuestosByServicioConEstadoAceptado(presupuesto.getServicio().getId());
		boolean b = a > 0;
		if(b == false) {
			this.save(presupuesto);
		} else {
			throw new PresupuestoYaAceptadoException();
		}
	}
	
	@Transactional(rollbackFor = ServicioNoAceptadoException.class)
	public void servicioNoAceptado(Presupuesto presupuesto) throws ServicioNoAceptadoException {
		if(presupuesto.getServicio().getEstado() == EstadoServicio.Aceptado) {
			this.save(presupuesto);
		} else {
			throw new ServicioNoAceptadoException();
		}
	}

}
