package org.springframework.samples.petclinic.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Event;

public interface EventRepository extends CrudRepository<Event, Integer> {}
