package org.springframework.samples.petclinic.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.TipoCategoria;
import org.springframework.samples.petclinic.model.Valoracion;

public interface ValoracionRepository extends CrudRepository<Valoracion, Integer>{

	@Query("SELECT sum(v.valor) FROM Valoracion v WHERE v.servicio.tipocategoria = :tipo")
	Integer getSumaValoracionesTipo(@Param("tipo") TipoCategoria tipo) throws DataAccessException;
	
	@Query("SELECT count(v) FROM Valoracion v WHERE v.servicio.tipocategoria = :tipo")
	Integer getCountValoracionesTipo(@Param("tipo") TipoCategoria tipo) throws DataAccessException;
	
	@Query("SELECT v.servicio.id FROM Valoracion v")
	List<Integer> serviciosConValoracion() throws DataAccessException;
}
