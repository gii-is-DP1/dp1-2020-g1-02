package org.springframework.samples.petclinic.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Instalacion;
import org.springframework.samples.petclinic.repository.InstalacionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InstalacionService {

	private InstalacionRepository instalacionRepo;
	
	@Autowired
	public InstalacionService(InstalacionRepository instalacionRepo) {
		super();
		this.instalacionRepo = instalacionRepo;
	}
	
	@Transactional(readOnly=true)
	public int instalacionCount() {
		return (int) instalacionRepo.count();
	}

	@Transactional(readOnly=true)
	public Iterable<Instalacion> findAll(){
		return instalacionRepo.findAll();
	}
	
	@Transactional
	public void save(Instalacion instalacion) {
		instalacionRepo.save(instalacion);
	}
	
	@Transactional
	public void delete(Instalacion instalacion) {
		instalacionRepo.delete(instalacion);
	}

	@Transactional(readOnly=true)
	public Optional<Instalacion> findInstalacionById(Integer instalacionId) {
		return instalacionRepo.findById(instalacionId);
	}
	
	@Transactional
	public void deleteById(Integer id) {
		Instalacion instalacionBorrar = findInstalacionById(id).get();
		delete(instalacionBorrar);
	}

	@Transactional(readOnly=true)
	public Iterable<Instalacion> findInstalacionByClienteName(String nombreCli) {
		return instalacionRepo.findAllByClienteName(nombreCli.toLowerCase());
	}

}
