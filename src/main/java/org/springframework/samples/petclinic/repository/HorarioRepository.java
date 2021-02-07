package org.springframework.samples.petclinic.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Horario;

public interface HorarioRepository extends CrudRepository<Horario, Integer> {
	
//	@Query("SELECT h FROM Horario h WHERE h.trabajador.name = ?1")
//	Iterable<Horario> findHorarioByTrabajadorId(String nameTrab);
	
	@Query("SELECT DISTINCT h FROM Horario h LEFT JOIN FETCH h.trabajador WHERE h.trabajador.nombre LIKE :nombreTrab%")
	Collection<Horario> findHorariosByTrabajadorName(@Param("nombreTrab") String nombreTrab);
	
	@Query("SELECT h FROM Horario h WHERE h.trabajador.id = :trabajador")
	Iterable<Horario> findHorariosByTrabajadorId(@Param("trabajador") Integer trabajador);
	
	@Query("SELECT count(*) FROM Horario h WHERE h.trabajador.id = :trabajador AND h.hora_inicio < :fin AND h.hora_fin > :inicio AND h.fecha=:fecha" )
	Integer findHorasSolapadas(@Param("trabajador") Integer trabajador, @Param("inicio") LocalTime inicio, @Param("fin") LocalTime fin, @Param("fecha") LocalDate fecha);

}
