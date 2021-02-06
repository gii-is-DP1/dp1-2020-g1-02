package org.springframework.samples.petclinic.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.ContratoTrabajador;

public interface ContratoTrabajadorRepository extends CrudRepository<ContratoTrabajador, Integer> {
	
	@Query("SELECT s FROM ContratoTrabajador s WHERE day(s.fechafinal) >= :dia and month(s.fechafinal) = :mes and year(s.fechafinal) = :anyo")
	Iterable<ContratoTrabajador> contratosTrabajadorQueCaducanEsteMes(@Param("dia") int dia, @Param("mes") int mes, @Param("anyo") int anyo) throws DataAccessException;
	
	@Query("SELECT c FROM ContratoTrabajador c WHERE c.trabajador.id=:trabajador")
	Iterable<ContratoTrabajador> contratosTrabajadorPorTrabajador(@Param("trabajador") Integer trabajador) throws DataAccessException;;
}
