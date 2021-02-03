package org.springframework.samples.petclinic.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.EstadoServicio;
import org.springframework.samples.petclinic.model.Servicio;
import org.springframework.samples.petclinic.repository.ServicioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServicioService {
	
	@Autowired
	private ServicioRepository servicioRepo;
	
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
	
	public Iterable<Servicio> serviciosByCliente(Integer id){
		return servicioRepo.serviciosByCliente(id);
	}
}
