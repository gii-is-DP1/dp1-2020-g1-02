package org.springframework.samples.petclinic.service;

import java.time.LocalDate;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.EstadoServicio;
import org.springframework.samples.petclinic.model.Valoracion;
import org.springframework.samples.petclinic.repository.ValoracionRepository;
import org.springframework.samples.petclinic.service.exceptions.AntesComenzarServicioException;
import org.springframework.samples.petclinic.service.exceptions.ServicioNoAceptadoException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ValoracionService {
	
	@Autowired
	private ValoracionRepository valoracionRepo;
	
	@Transactional(readOnly=true)
	public int valoracionCount() {
		return (int) valoracionRepo.count();
	}
	
	@Transactional(readOnly=true)
	public Iterable<Valoracion> findAll(){
		return valoracionRepo.findAll();
	}
	
	@Transactional
	public void save(Valoracion valoracion) {
		valoracionRepo.save(valoracion);
	}
	
	@Transactional
	public void delete(Valoracion valoracion) {
		valoracionRepo.delete(valoracion);
		
	}
	
	@Transactional(readOnly=true)
	public Optional<Valoracion> findValoracionById(int valoracionId) {
		// TODO Auto-generated method stub
		return valoracionRepo.findById(valoracionId);
	}
	
	@Transactional(readOnly=true)
	public Iterable<Valoracion> findValoracionByServicioId(String name) {
		return null;
		//return valoracionRepo.findValoracionByServicioId(name);
	}
	
	@Transactional
	public void deleteById(Integer id) {
		Valoracion valoracionBorrar = findValoracionById(id).get();
		delete(valoracionBorrar);
	}

	@Transactional(readOnly=true)
	public Iterable<Valoracion> findValoracionByClienteName(String nombre) {
		return valoracionRepo.findAllByClienteName(nombre.toLowerCase());
	}
	
	@Transactional(rollbackFor = ServicioNoAceptadoException.class)
	public void valoracionServicioNoAceptado(Valoracion valoracion) throws ServicioNoAceptadoException {
		if(valoracion.getServicio().getEstado() != EstadoServicio.Aceptado) {
			throw new ServicioNoAceptadoException();
		}
	}
	
	@Transactional(rollbackFor = AntesComenzarServicioException.class)
	public void valoracionAntesComenzarServicio(Valoracion valoracion) throws AntesComenzarServicioException {
		if(valoracion.getServicio().getFechainicio().isAfter(LocalDate.now())) {
			throw new AntesComenzarServicioException();
		}
	}
	
	@Transactional
	public void comprobarExcepciones(Valoracion valoracion) throws ServicioNoAceptadoException, AntesComenzarServicioException {
		this.valoracionServicioNoAceptado(valoracion);
		this.valoracionAntesComenzarServicio(valoracion);
		this.save(valoracion);
	}

}
