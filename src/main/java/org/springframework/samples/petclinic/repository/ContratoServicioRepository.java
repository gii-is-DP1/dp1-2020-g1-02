package org.springframework.samples.petclinic.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.ContratoServicio;

public interface ContratoServicioRepository extends CrudRepository<ContratoServicio, Integer>{
	
	
	@Query("SELECT cliente FROM ContratoServicio cs WHERE cs.fechapago = null")
	Iterable<ContratoServicio> buscaMorosos() throws DataAccessException;
	
	@Query("SELECT s FROM ContratoServicio s WHERE day(s.fechafinal) >= :dia and month(s.fechafinal) = :mes and year(s.fechafinal) = :anyo")
	Iterable<ContratoServicio> contratosQueCaducanEsteMes(@Param("dia") int dia, @Param("mes") int mes, @Param("anyo") int anyo) throws DataAccessException;
}
