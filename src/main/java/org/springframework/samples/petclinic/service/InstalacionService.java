package org.springframework.samples.petclinic.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Instalacion;
import org.springframework.samples.petclinic.repository.InstalacionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InstalacionService {
	@Autowired
	private InstalacionRepository instalacionRepo;
	
	@Transactional
	public int instalacionCount() {
		return (int) instalacionRepo.count();
	}
	
	@Transactional
	public Iterable<Instalacion> findAll(){
		return instalacionRepo.findAll();
	}

	public void save(Instalacion instalacion) {
		instalacionRepo.save(instalacion);
	}
	
	public void delete(Instalacion instalacion) {
		instalacionRepo.delete(instalacion);
		
	}

	public Optional<Instalacion> findInstalacionById(Integer instalacionId) {
		return instalacionRepo.findById(instalacionId);
	}

}
