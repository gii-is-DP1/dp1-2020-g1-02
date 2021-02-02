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
		// TODO Auto-generated method stub
		return reclamacionRepo.findById(reclamacionId);
	}
	
	@Transactional(readOnly=true)
	public Iterable<Reclamacion> findReclamacionesByServicioId(String name) {
		return null;
		//return reclamacionRepo.findReclamacionesByServicioId(name);
	}
	
	@Transactional
	public void deleteById(Integer id) {
		Reclamacion reclamacionBorrar = findReclamacionById(id).get();
		delete(reclamacionBorrar);
	}

}
