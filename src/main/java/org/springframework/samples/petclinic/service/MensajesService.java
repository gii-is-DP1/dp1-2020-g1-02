package org.springframework.samples.petclinic.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Mensaje;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.repository.MensajesRepository;
import org.springframework.samples.petclinic.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MensajesService {
	
	private MensajesRepository mensajesRepo;
	private  UserRepository userRepo;
	
	@Autowired
	public MensajesService(MensajesRepository mensajesRepo, UserRepository userRepo) {
		this.mensajesRepo = mensajesRepo;
		this.userRepo = userRepo;
	}
	@Transactional
	public <S extends Mensaje> S save(S entity) {
		Mensaje mensaje = mensajesRepo.save(entity);
		User emisor = entity.getEmisor();
		List<Mensaje> menEnviados = emisor.getMensajesEnviados();
		menEnviados.add(mensaje);
		emisor.setMensajesEnviados(menEnviados);
		userRepo.save(emisor);
		
		List<User> receptores = entity.getReceptores();
		
		for(User receptor : receptores) {
			List<Mensaje> menRecibidos = receptor.getMensajesRecibidos();
			menRecibidos.add(mensaje);
			receptor.setMensajesRecibidos(menRecibidos);
			userRepo.save(receptor);
		}
		
		return (S) mensaje;
	}
	@Transactional
	public <S extends Mensaje> Iterable<S> saveAll(Iterable<S> entities) {
		return mensajesRepo.saveAll(entities);
	}
	@Transactional(readOnly=true)
	public Optional<Mensaje> findById(Integer id) {
		return mensajesRepo.findById(id);
	}
	@Transactional(readOnly=true)
	public boolean existsById(Integer id) {
		return mensajesRepo.existsById(id);
	}
	
	@Transactional(readOnly=true)
	public Iterable<Mensaje> findAll() {
		return mensajesRepo.findAll();
	}
	@Transactional(readOnly=true)
	public Iterable<Mensaje> findAllById(Iterable<Integer> ids) {
		return mensajesRepo.findAllById(ids);
	}
	@Transactional(readOnly=true)
	public Collection<Mensaje> findAllByReceptor(User receptor) throws DataAccessException {
		return mensajesRepo.findAllByReceptor(receptor.getUsername());
	}
	@Transactional(readOnly=true)
	public long count() {
		return mensajesRepo.count();
	}
	@Transactional
	public void deleteById(Integer id) {
		mensajesRepo.deleteById(id);
	}
	
	public void vaciarReceptores(Mensaje mensaje) {
		List<User> receptores = (List<User>) userRepo.findReceptoresByMensaje(mensaje.getId());
		for(User u : receptores) {
			u.getMensajesRecibidos().remove(mensaje);
		}
	}
	

	@Transactional
	public void delete(Mensaje mensaje) {
		vaciarReceptores(mensaje);
		mensajesRepo.delete(mensaje);
	}
	@Transactional
	public void deleteAll(Iterable<? extends Mensaje> entities) {
		mensajesRepo.deleteAll(entities);
	}
	@Transactional
	public void deleteAll() {
		mensajesRepo.deleteAll();
	}

	@Transactional
	public void marcarLeido(Mensaje m) {
		m.setLeido(true);
		mensajesRepo.save(m);
	}
	
	
}
