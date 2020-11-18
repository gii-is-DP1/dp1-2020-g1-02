package org.springframework.samples.petclinic.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepo;
	
	@Transactional
	public int clienteCount() {
		return (int) clienteRepo.count();
	}
	
	@Transactional
	public Iterable<Cliente> findAll(){
		return clienteRepo.findAll();
	}

	public void save(Cliente cliente) {
		clienteRepo.save(cliente);
	}
	
	public void delete(Cliente cliente) {
		clienteRepo.delete(cliente);
		
	}

//	public Optional<Cliente> findClienteByDNI(String dni) {
//		// TODO Auto-generated method stub
//		return clienteRepo.findByDNI(dni);
//	}
}
