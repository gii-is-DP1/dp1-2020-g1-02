package org.springframework.samples.petclinic.repository;

import java.time.LocalDateTime;
import java.util.Collection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Horario;
import org.springframework.samples.petclinic.model.Servicio;

public interface HorarioRepository extends CrudRepository<Horario, Integer> {
	
//	@Query("SELECT h FROM Horario h WHERE h.trabajador.name = ?1")
//	Iterable<Horario> findHorarioByTrabajadorId(String nameTrab);
	
	@Query("SELECT DISTINCT h FROM Horario h LEFT JOIN FETCH h.trabajador WHERE h.trabajador.nombre LIKE :nombreTrab%")
	Collection<Horario> findHorariosByTrabajadorName(@Param("nombreTrab") String nombreTrab);
	
	@Query("SELECT h FROM Horario h WHERE h.trabajador.id = :trabajador")
	Iterable<Horario> findHorariosByTrabajadorId(@Param("trabajador") Integer trabajador);
	
	@Query("SELECT count(*) FROM Horario h WHERE h.trabajador.id = :trabajador AND h.hora_inicio < :fin AND h.hora_fin > :inicio")
	Integer findHorasSolapadas(@Param("trabajador") Integer trabajador, @Param("inicio") LocalDateTime inicio, @Param("fin") LocalDateTime fin);

}
