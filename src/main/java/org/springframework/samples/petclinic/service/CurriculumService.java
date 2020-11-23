package org.springframework.samples.petclinic.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Curriculum;
import org.springframework.samples.petclinic.repository.CurriculumRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CurriculumService {

	@Autowired
	private CurriculumRepository curriculumRepo;
	
	@Transactional
	public int curriculumCount() {
		return (int) curriculumRepo.count();
	}
	
	@Transactional
	public Iterable<Curriculum> findAll(){
		return curriculumRepo.findAll();
	}

	public void save(Curriculum curriculum) {
		curriculumRepo.save(curriculum);
	}
	
	public void delete(Curriculum curriculum) {
		curriculumRepo.delete(curriculum);
		
	}

	public Optional<Curriculum> findCurriculumById(Integer curriculumId) {
		return curriculumRepo.findById(curriculumId);
	}
}

