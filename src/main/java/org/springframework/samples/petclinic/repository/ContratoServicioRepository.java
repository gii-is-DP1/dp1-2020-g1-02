package org.springframework.samples.petclinic.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import org.springframework.samples.petclinic.model.ContratoServicio;

public interface ContratoServicioRepository extends CrudRepository<ContratoServicio, Integer>{
	
	
	@Query("SELECT cliente FROM ContratoServicio cs WHERE cs.fechapago = null")
	Iterable<ContratoServicio> buscaMorosos() throws DataAccessException;
}
