package org.springframework.samples.petclinic.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Mensaje;

public interface MensajesRepository extends CrudRepository<Mensaje, Integer> {

	@Query("SELECT u.mensajesRecibidos FROM User u WHERE u.username = :receptor")
	Collection<Mensaje> findAllByReceptor(@Param("receptor") String receptor);
	
}
