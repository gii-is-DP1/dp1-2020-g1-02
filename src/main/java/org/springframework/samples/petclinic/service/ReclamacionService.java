package org.springframework.samples.petclinic.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Reclamacion;
import org.springframework.samples.petclinic.repository.ReclamacionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReclamacionService {
	@Autowired
	private ReclamacionRepository reclamacionRepo;
	
	@Transactional
	public int reclamacionCount() {
		return (int) reclamacionRepo.count();
	}
	
	@Transactional
	public Iterable<Reclamacion> findAll(){
		return reclamacionRepo.findAll();
	}

	public void save(Reclamacion reclamacion) {
		reclamacionRepo.save(reclamacion);
	}
	
	public void delete(Reclamacion reclamacion) {
		reclamacionRepo.delete(reclamacion);
		
	}

	public Optional<Reclamacion> findReclamacionById(int reclamacionId) {
		// TODO Auto-generated method stub
		return reclamacionRepo.findById(reclamacionId);
	}
	
	public Iterable<Reclamacion> findReclamacionesByServicioId(String name) {
		return reclamacionRepo.findReclamacionesByServicioId(name);
	}
	
	public void deleteById(Integer id) {
		Reclamacion reclamacionBorrar = findReclamacionById(id).get();
		delete(reclamacionBorrar);
	}

}
