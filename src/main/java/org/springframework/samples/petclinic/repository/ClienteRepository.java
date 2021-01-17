package org.springframework.samples.petclinic.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Cliente;


public interface ClienteRepository extends CrudRepository<Cliente, Integer> {

	@Query("SELECT c FROM Cliente c WHERE c.user.username = :username")
	Optional<Cliente> findClienteByUsername(@Param("username") String username);
	
	
	

}
