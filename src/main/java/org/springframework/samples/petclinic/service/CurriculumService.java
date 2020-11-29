package org.springframework.samples.petclinic.service;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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


	public Iterable<Curriculum> findCurriculumsByTrabajadorId(Integer idTrabajador) {
		List<Curriculum> listaCurriculums = new ArrayList<Curriculum>();
		Iterable<Curriculum> curriculums = curriculumRepo.findAll();
		Iterator<Curriculum> iterador = curriculums.iterator();
		while(iterador.hasNext()) {
			Curriculum f = (Curriculum) iterador.next();
			if(f.getId_trab()==idTrabajador) listaCurriculums.add(f);
		}
		Iterable<Curriculum> facturasProv = listaCurriculums;
		return facturasProv;
	}
	
	public void deleteById(Integer id) {
		Curriculum curriculumBorrar = findCurriculumById(id).get();
		delete(curriculumBorrar);
	}

	public void saveById(Integer id) {
		Curriculum curriculumSalvado = findCurriculumById(id).get();
		save(curriculumSalvado);
	}

}

