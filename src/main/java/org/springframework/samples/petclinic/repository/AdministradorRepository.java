package org.springframework.samples.petclinic.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Administrador;

public interface AdministradorRepository extends CrudRepository<Administrador, Integer>{
	@Query("SELECT a FROM Administrador a WHERE a.user.username = :username")
	Optional<Administrador> findAdministradorByUsername(@Param("username") String username);
}
