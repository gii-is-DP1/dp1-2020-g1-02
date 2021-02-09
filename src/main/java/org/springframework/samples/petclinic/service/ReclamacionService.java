package org.springframework.samples.petclinic.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.EstadoServicio;
import org.springframework.samples.petclinic.model.Reclamacion;
import org.springframework.samples.petclinic.repository.ReclamacionRepository;
import org.springframework.samples.petclinic.service.exceptions.NoDuranteServicioException;
import org.springframework.samples.petclinic.service.exceptions.ServicioNoAceptadoException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReclamacionService {

	private ReclamacionRepository reclamacionRepo;
	
	@Autowired
	public ReclamacionService(ReclamacionRepository reclamacionRepo) {
		super();
		this.reclamacionRepo = reclamacionRepo;
	}

	@Transactional(readOnly=true)
	public int reclamacionCount() {
		return (int) reclamacionRepo.count();
	}
	
	@Transactional(readOnly=true)
	public Iterable<Reclamacion> findAll(){
		return reclamacionRepo.findAll();
	}

	@Transactional
	public void save(Reclamacion reclamacion) {
		reclamacionRepo.save(reclamacion);
	}
	
	@Transactional
	public void delete(Reclamacion reclamacion) {
		reclamacionRepo.delete(reclamacion);
	}

	@Transactional(readOnly=true)
	public Optional<Reclamacion> findReclamacionById(int reclamacionId) {
		return reclamacionRepo.findById(reclamacionId);
	}
	
	@Transactional(readOnly=true)
	public Iterable<Reclamacion> findReclamacionesByServicioId(Integer id) {
		return reclamacionRepo.findReclamacionesByServicioId(id);
	}
	
	@Transactional
	public void deleteById(Integer id) {
		Reclamacion reclamacionBorrar = findReclamacionById(id).get();
		delete(reclamacionBorrar);
	}
	
	@Transactional(rollbackFor = ServicioNoAceptadoException.class)
	public void reclamacionServicioNoAceptado(Reclamacion reclamacion) throws ServicioNoAceptadoException {
		if(reclamacion.getServicio().getEstado() != EstadoServicio.Aceptado) {
			throw new ServicioNoAceptadoException();
		}
	}
	
	@Transactional(rollbackFor = NoDuranteServicioException.class)
	public void reclamacionDuranteServicio(Reclamacion reclamacion) throws NoDuranteServicioException {
		if(LocalDate.now().isAfter(reclamacion.getServicio().getFechafin()) ||
				LocalDate.now().isBefore(reclamacion.getServicio().getFechainicio())) {
			throw new NoDuranteServicioException();
		}
	}
	
	@Transactional
	public void comprobarExcepciones(Reclamacion reclamacion) throws ServicioNoAceptadoException, NoDuranteServicioException {
		this.reclamacionServicioNoAceptado(reclamacion);
		this.reclamacionDuranteServicio(reclamacion);
		this.save(reclamacion);
	}

}
