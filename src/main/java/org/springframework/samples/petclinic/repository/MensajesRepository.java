package org.springframework.samples.petclinic.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Mensajes;
import org.springframework.samples.petclinic.model.User;

public interface MensajesRepository extends CrudRepository<Mensajes, Integer> {

	@Query
	Collection<User> findAllByUsername();
}
