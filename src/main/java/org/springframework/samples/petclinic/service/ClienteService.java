package org.springframework.samples.petclinic.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteService {

	private ClienteRepository clienteRepo;
	
	private UserService userService;
	
	
	private AuthoritiesService authoritiesService;
	
	@Autowired
	public ClienteService(ClienteRepository clienteRepo, UserService userService,
			AuthoritiesService authoritiesService) {
		super();
		this.clienteRepo = clienteRepo;
		this.userService = userService;
		this.authoritiesService = authoritiesService;
	}

	@Transactional(readOnly=true)
	public int clienteCount() {
		return (int) clienteRepo.count();
	}
	
	@Transactional(readOnly=true)
	public Iterable<Cliente> findAll(){
		return clienteRepo.findAll();
	}

	@Transactional
	public void save(Cliente cliente) {
		clienteRepo.save(cliente);
	}
	
	@Transactional
	public void delete(Cliente cliente) {
		clienteRepo.delete(cliente);
		
	}
	
	@Transactional
	public void deleteById(Integer id) {
		Cliente clienteBorrar = findClienteById(id).get();
		delete(clienteBorrar);
	}

	@Transactional(readOnly=true)
	public Optional<Cliente> findClienteById(Integer clienteId) {
		return clienteRepo.findById(clienteId);
	}
	
	@Transactional(readOnly=true)
	public Optional<Cliente> findClienteByUsername(String clienteUsername) {
		return clienteRepo.findClienteByUsername(clienteUsername);
	}
	
	@Transactional
	public void saveCliente(Cliente client) throws DataAccessException {
		//creating owner
		clienteRepo.save(client);
		//creating user
		
		userService.saveUser(client.getUser());
		//creating authorities
		authoritiesService.saveAuthorities(client.getUser().getUsername(), "cliente");	
	}
	
	@Transactional
	public void actualizarCliente(Cliente client) throws DataAccessException {
		clienteRepo.save(client);
	}
	
	

}
