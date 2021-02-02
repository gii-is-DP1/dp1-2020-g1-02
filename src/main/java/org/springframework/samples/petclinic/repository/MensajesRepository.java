package org.springframework.samples.petclinic.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Mensaje;
import org.springframework.samples.petclinic.model.User;

public interface MensajesRepository extends CrudRepository<Mensaje, Integer> {

	@Query
	Collection<Mensaje> findAllByReceptor(User receptor);
}
