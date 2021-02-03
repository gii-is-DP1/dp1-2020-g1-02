package org.springframework.samples.petclinic.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.EstadoServicio;
import org.springframework.samples.petclinic.model.Presupuesto;
import org.springframework.samples.petclinic.repository.PresupuestoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PresupuestoService {
	
	@Autowired
	private PresupuestoRepository presupuestoRepo;
	
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
	}
	
	@Transactional
	public void rechazar(Presupuesto presupuesto) {
		presupuesto.setEstado(EstadoServicio.Rechazado);
		presupuestoRepo.save(presupuesto);
	}
	
	
	public Integer numeroPresupuestosByServicioConEstadoAceptado(Integer id) {
		return presupuestoRepo.numeroPresupuestosByServicioConEstadoAceptado(id);
	}

}
