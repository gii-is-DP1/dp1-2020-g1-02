package org.springframework.samples.petclinic.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Mensajes;
import org.springframework.samples.petclinic.repository.MensajesRepository;
import org.springframework.stereotype.Service;

@Service
public class MensajesService {
	
	private MensajesRepository mensajesAdminRepo;
	
	@Autowired
	public MensajesService(MensajesRepository mensajesAdminRepo) {
		this.mensajesAdminRepo = mensajesAdminRepo;
	}

	public <S extends Mensajes> S save(S entity) {
		return mensajesAdminRepo.save(entity);
	}

	public <S extends Mensajes> Iterable<S> saveAll(Iterable<S> entities) {
		return mensajesAdminRepo.saveAll(entities);
	}

	public Optional<Mensajes> findById(Integer id) {
		return mensajesAdminRepo.findById(id);
	}

	public boolean existsById(Integer id) {
		return mensajesAdminRepo.existsById(id);
	}

	public Iterable<Mensajes> findAll() {
		return mensajesAdminRepo.findAll();
	}

	public Iterable<Mensajes> findAllById(Iterable<Integer> ids) {
		return mensajesAdminRepo.findAllById(ids);
	}

	public long count() {
		return mensajesAdminRepo.count();
	}

	public void deleteById(Integer id) {
		mensajesAdminRepo.deleteById(id);
	}

	public void delete(Mensajes entity) {
		mensajesAdminRepo.delete(entity);
	}

	public void deleteAll(Iterable<? extends Mensajes> entities) {
		mensajesAdminRepo.deleteAll(entities);
	}

	public void deleteAll() {
		mensajesAdminRepo.deleteAll();
	}

	
	
}
