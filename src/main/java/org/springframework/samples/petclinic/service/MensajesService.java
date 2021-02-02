package org.springframework.samples.petclinic.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Mensaje;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.repository.MensajesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MensajesService {
	
	private MensajesRepository mensajesAdminRepo;
	
	@Autowired
	public MensajesService(MensajesRepository mensajesAdminRepo) {
		this.mensajesAdminRepo = mensajesAdminRepo;
	}
	@Transactional
	public <S extends Mensaje> S save(S entity) {
		return mensajesAdminRepo.save(entity);
	}
	@Transactional
	public <S extends Mensaje> Iterable<S> saveAll(Iterable<S> entities) {
		return mensajesAdminRepo.saveAll(entities);
	}
	@Transactional(readOnly=true)
	public Optional<Mensaje> findById(Integer id) {
		return mensajesAdminRepo.findById(id);
	}
	@Transactional(readOnly=true)
	public boolean existsById(Integer id) {
		return mensajesAdminRepo.existsById(id);
	}
	
	@Transactional(readOnly=true)
	public Iterable<Mensaje> findAll() {
		return mensajesAdminRepo.findAll();
	}
	@Transactional(readOnly=true)
	public Iterable<Mensaje> findAllById(Iterable<Integer> ids) {
		return mensajesAdminRepo.findAllById(ids);
	}
	@Transactional(readOnly=true)
	public Collection<Mensaje> findAllByReceptor(User receptor) throws DataAccessException {
		return mensajesAdminRepo.findAllByReceptor(receptor);
	}
	@Transactional(readOnly=true)
	public long count() {
		return mensajesAdminRepo.count();
	}
	@Transactional
	public void deleteById(Integer id) {
		mensajesAdminRepo.deleteById(id);
	}
	@Transactional
	public void delete(Mensaje entity) {
		mensajesAdminRepo.delete(entity);
	}
	@Transactional
	public void deleteAll(Iterable<? extends Mensaje> entities) {
		mensajesAdminRepo.deleteAll(entities);
	}
	@Transactional
	public void deleteAll() {
		mensajesAdminRepo.deleteAll();
	}

	
	
}
