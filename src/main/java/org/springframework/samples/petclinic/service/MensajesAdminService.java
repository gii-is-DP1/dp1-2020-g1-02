package org.springframework.samples.petclinic.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.MensajesAdmin;
import org.springframework.samples.petclinic.repository.MensajesAdminRepository;
import org.springframework.stereotype.Service;

@Service
public class MensajesAdminService {
	
	private MensajesAdminRepository mensajesAdminRepo;
	
	@Autowired
	public MensajesAdminService(MensajesAdminRepository mensajesAdminRepo) {
		this.mensajesAdminRepo = mensajesAdminRepo;
	}

	public <S extends MensajesAdmin> S save(S entity) {
		return mensajesAdminRepo.save(entity);
	}

	public <S extends MensajesAdmin> Iterable<S> saveAll(Iterable<S> entities) {
		return mensajesAdminRepo.saveAll(entities);
	}

	public Optional<MensajesAdmin> findById(Integer id) {
		return mensajesAdminRepo.findById(id);
	}

	public boolean existsById(Integer id) {
		return mensajesAdminRepo.existsById(id);
	}

	public Iterable<MensajesAdmin> findAll() {
		return mensajesAdminRepo.findAll();
	}

	public Iterable<MensajesAdmin> findAllById(Iterable<Integer> ids) {
		return mensajesAdminRepo.findAllById(ids);
	}

	public long count() {
		return mensajesAdminRepo.count();
	}

	public void deleteById(Integer id) {
		mensajesAdminRepo.deleteById(id);
	}

	public void delete(MensajesAdmin entity) {
		mensajesAdminRepo.delete(entity);
	}

	public void deleteAll(Iterable<? extends MensajesAdmin> entities) {
		mensajesAdminRepo.deleteAll(entities);
	}

	public void deleteAll() {
		mensajesAdminRepo.deleteAll();
	}

	
	
}
