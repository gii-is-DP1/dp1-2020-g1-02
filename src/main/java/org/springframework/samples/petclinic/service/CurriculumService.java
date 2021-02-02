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
	
	@Transactional(readOnly=true)
	public int curriculumCount() {
		return (int) curriculumRepo.count();
	}
	
	@Transactional(readOnly=true)
	public Iterable<Curriculum> findAll(){
		return curriculumRepo.findAll();
	}

	@Transactional
	public void save(Curriculum curriculum) {
		curriculumRepo.save(curriculum);
	}
	
	@Transactional
	public void delete(Curriculum curriculum) {
		curriculumRepo.delete(curriculum);
		
	}

	@Transactional(readOnly=true)
	public Optional<Curriculum> findCurriculumById(Integer curriculumId) {
		return curriculumRepo.findById(curriculumId);
	}

	@Transactional(readOnly=true)
	public Iterable<Curriculum> findCurriculumByTrabajadorId(String name) {
		return null;
		//return curriculumRepo.findCurriculumByTrabajadorId(name);
	}
	
	@Transactional
	public void deleteById(Integer id) {
		Curriculum curriculumBorrar = findCurriculumById(id).get();
		delete(curriculumBorrar);
	}

}

