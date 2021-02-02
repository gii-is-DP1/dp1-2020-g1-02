package org.springframework.samples.petclinic.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Reclamacion;
import org.springframework.samples.petclinic.model.Valoracion;
import org.springframework.samples.petclinic.repository.ReclamacionRepository;
import org.springframework.samples.petclinic.repository.ValoracionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ValoracionService {
	
	@Autowired
	private ValoracionRepository valoracionRepo;
	
	@Transactional
	public int valoracionCount() {
		return (int) valoracionRepo.count();
	}
	
	@Transactional
	public Iterable<Valoracion> findAll(){
		return valoracionRepo.findAll();
	}

	public void save(Valoracion valoracion) {
		valoracionRepo.save(valoracion);
	}
	
	public void delete(Valoracion valoracion) {
		valoracionRepo.delete(valoracion);
		
	}

	public Optional<Valoracion> findValoracionById(int valoracionId) {
		// TODO Auto-generated method stub
		return valoracionRepo.findById(valoracionId);
	}
	
	public Iterable<Valoracion> findValoracionByServicioId(String name) {
		return null;
		//return valoracionRepo.findValoracionByServicioId(name);
	}
	
	public void deleteById(Integer id) {
		Valoracion valoracionBorrar = findValoracionById(id).get();
		delete(valoracionBorrar);
	}

}
