package org.springframework.samples.petclinic.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.EstadoServicio;
import org.springframework.samples.petclinic.model.Horario;
import org.springframework.samples.petclinic.model.Servicio;
import org.springframework.samples.petclinic.model.Trabajador;
import org.springframework.samples.petclinic.repository.ServicioRepository;
import org.springframework.samples.petclinic.repository.TrabajadorRepository;
import org.springframework.samples.petclinic.service.exceptions.HorarioServicioException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServicioService {
	
	@Autowired
	private ServicioRepository servicioRepo;
	
	@Autowired
	private TrabajadorService trabajadorService;
	
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
	}
	
	@Transactional
	public void rechazar(Servicio servicio) {
		servicio.setEstado(EstadoServicio.Rechazado);
		servicioRepo.save(servicio);
	}
	
//	public boolean cumpleNoEsLaMismaHora(Servicio servicio) {
//		Boolean res = true;
//		Trabajador trabajador = trabajadorService.findTrabajadorByUsername(servicio.get)
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
//	public void crearServicio(Servicio servicio) throws HorarioServicioException {
//		if (this.cumpleNoEsLaMismaHora(servicio)) {
//			this.save(servicio);
//		}else {
//			throw new HorarioServicioException();
//		}
//	}
//	
	public Iterable<Servicio> serviciosByCliente(Integer id){
		return servicioRepo.serviciosByCliente(id);
	}
}
