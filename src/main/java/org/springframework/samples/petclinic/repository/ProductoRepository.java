package org.springframework.samples.petclinic.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Producto;

public interface ProductoRepository extends CrudRepository<Producto, Integer> {

	@Modifying
	@Query("UPDATE Producto p SET p.cantidad= ?2 WHERE p.id = ?1")
	void restarProducto(Integer productoId, Integer cant);
	
	@Query("SELECT p FROM Producto p WHERE p.name = ?1")
	Optional<Producto> findByName(String name);
	
}
