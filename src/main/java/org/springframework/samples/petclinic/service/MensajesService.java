package org.springframework.samples.petclinic.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Mensaje;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.repository.MensajesRepository;
import org.springframework.stereotype.Service;

@Service
public class MensajesService {
	
	private MensajesRepository mensajesAdminRepo;
	
	@Autowired
	public MensajesService(MensajesRepository mensajesAdminRepo) {
		this.mensajesAdminRepo = mensajesAdminRepo;
	}

	public <S extends Mensaje> S save(S entity) {
		return mensajesAdminRepo.save(entity);
	}

	public <S extends Mensaje> Iterable<S> saveAll(Iterable<S> entities) {
		return mensajesAdminRepo.saveAll(entities);
	}

	public Optional<Mensaje> findById(Integer id) {
		return mensajesAdminRepo.findById(id);
	}

	public boolean existsById(Integer id) {
		return mensajesAdminRepo.existsById(id);
	}

	public Iterable<Mensaje> findAll() {
		return mensajesAdminRepo.findAll();
	}

	public Iterable<Mensaje> findAllById(Iterable<Integer> ids) {
		return mensajesAdminRepo.findAllById(ids);
	}
	
	public Collection<Mensaje> findAllByReceptor(User receptor) throws DataAccessException {
		return mensajesAdminRepo.findAllByReceptor(receptor);
	}

	public long count() {
		return mensajesAdminRepo.count();
	}

	public void deleteById(Integer id) {
		mensajesAdminRepo.deleteById(id);
	}

	public void delete(Mensaje entity) {
		mensajesAdminRepo.delete(entity);
	}

	public void deleteAll(Iterable<? extends Mensaje> entities) {
		mensajesAdminRepo.deleteAll(entities);
	}

	public void deleteAll() {
		mensajesAdminRepo.deleteAll();
	}

	
	
}
