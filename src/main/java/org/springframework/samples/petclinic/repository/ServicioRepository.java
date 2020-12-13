package org.springframework.samples.petclinic.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Servicio;

public interface ServicioRepository extends CrudRepository<Servicio, Integer> {

}
