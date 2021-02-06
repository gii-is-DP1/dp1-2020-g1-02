package org.springframework.samples.petclinic.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Trabajador;

public interface TrabajadorRepository extends CrudRepository<Trabajador, Integer> {
	@Query("SELECT t FROM Trabajador t WHERE t.user.username = :username")
	Optional<Trabajador> findTrabajadorByUsername(@Param("username") String username);
	

	@Query("SELECT s.trabajadores FROM Servicio s WHERE s.id=:servicio")
	Iterable<Trabajador> trabajadoresByServicio(@Param("servicio") Integer servicio);

	@Query("SELECT t.nombre FROM Trabajador t")
	List<String> findAllNames();

}
