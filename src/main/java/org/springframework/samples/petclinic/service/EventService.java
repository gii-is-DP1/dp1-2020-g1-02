package org.springframework.samples.petclinic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Event;
import org.springframework.samples.petclinic.repository.EventRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EventService {
	@Autowired
	private EventRepository eventRepo;
	
	@Transactional
	public int eventCount() {
		return (int) eventRepo.count();
	}
	
	@Transactional
	public Iterable<Event> findAll(){
		return eventRepo.findAll();
	}
}
