package org.springframework.samples.petclinic.service;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Mensaje;
import org.springframework.samples.petclinic.model.Servicio;
import org.springframework.samples.petclinic.model.Trabajador;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Mostly used as a facade for all Petclinic controllers Also a placeholder
 * for @Transactional and @Cacheable annotations
 *
 * @author Michael Isvy
 * 
 */

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MensajesService mensajesService;


	private static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	

	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}


	@Transactional
	public void saveUser(User user) throws DataAccessException {
		user.setEnabled(true);

		String pass = user.getPassword();
		user.setPassword(encoder.encode(pass));
		userRepository.save(user);
	}
	
	@Transactional
	public Optional<User> findUser(String username) {
		return userRepository.findById(username);
	}
	
	@Transactional(readOnly=true)
	public User getLoggedUser() {
		return this.findUser(SecurityContextHolder.getContext().getAuthentication().getName()).get();
	}

	@Transactional(readOnly=true)
	public Iterable<User> findAll(){
		return userRepository.findAll();
	}
	
	@Transactional(readOnly=true)
	public List<String> findAllUsernames() {
		List<String> list = new ArrayList<String>();
		Iterable<User> users = findAll();
		Iterator<User> it = users.iterator();
		while(it.hasNext()) {
			list.add(it.next().getUsername());
		}
		return list;
	}
	@Transactional(readOnly=true)
	public List<User> findReceptoresByMensaje(Mensaje mensaje) {
		return userRepository.findReceptoresByMensaje(mensaje.getId());
	}
	
	public void vaciarMensajesRecibidos(User user) {
		List<Mensaje> mensajesRecibidos = (List<Mensaje>) mensajesService.findAllByReceptor(user);
		for(Mensaje m : mensajesRecibidos) {
			m.getReceptores().remove(user);
		}
	}
	@Transactional
	public void delete(User user) {
		vaciarMensajesRecibidos(user);
		userRepository.delete(user);
	}
	
	
	
}
