package org.springframework.samples.petclinic.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.EstadoServicio;
import org.springframework.samples.petclinic.model.TipoCategoria;
import org.springframework.samples.petclinic.model.Valoracion;
import org.springframework.samples.petclinic.repository.ValoracionRepository;
import org.springframework.samples.petclinic.service.exceptions.AntesComenzarServicioException;
import org.springframework.samples.petclinic.service.exceptions.ServicioNoAceptadoException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ValoracionService {

	private ValoracionRepository valoracionRepo;
	
	@Autowired
	public ValoracionService(ValoracionRepository valoracionRepo) {
		super();
		this.valoracionRepo = valoracionRepo;
	}

	@Transactional(readOnly=true)
	public int valoracionCount() {
		return (int) valoracionRepo.count();
	}
	
	@Transactional(readOnly=true)
	public Iterable<Valoracion> findAll(){
		return valoracionRepo.findAll();
	}
	
	@Transactional
	public void save(Valoracion valoracion) {
		valoracionRepo.save(valoracion);
	}
	
	@Transactional
	public void delete(Valoracion valoracion) {
		valoracionRepo.delete(valoracion);
		
	}
	
	@Transactional(readOnly=true)
	public Optional<Valoracion> findValoracionById(int valoracionId) {
		return valoracionRepo.findById(valoracionId);
	}
	
	@Transactional
	public void deleteById(Integer id) {
		Valoracion valoracionBorrar = findValoracionById(id).get();
		delete(valoracionBorrar);
	}
	
	@Transactional(readOnly=true)
	public Integer getMediaValoracionTipo(TipoCategoria tipo) {

		Integer suma = valoracionRepo.getSumaValoracionesTipo(tipo);
		Integer media = suma/valoracionRepo.getCountValoracionesTipo(tipo);
		return media;
	}
	
	@Transactional(rollbackFor = ServicioNoAceptadoException.class)
	public void valoracionServicioNoAceptado(Valoracion valoracion) throws ServicioNoAceptadoException {
		if(valoracion.getServicio().getEstado() != EstadoServicio.Aceptado) {
			throw new ServicioNoAceptadoException();
		}
	}
	
	@Transactional(rollbackFor = AntesComenzarServicioException.class)
	public void valoracionAntesComenzarServicio(Valoracion valoracion) throws AntesComenzarServicioException {
		if(valoracion.getServicio().getFechainicio().isAfter(LocalDate.now())) {
			throw new AntesComenzarServicioException();
		}
	}
	
	@Transactional
	public void comprobarExcepciones(Valoracion valoracion) throws ServicioNoAceptadoException, AntesComenzarServicioException {
		this.valoracionServicioNoAceptado(valoracion);
		this.valoracionAntesComenzarServicio(valoracion);
		this.save(valoracion);
	}
	
	@Transactional(readOnly=true)
	public Map<TipoCategoria, Integer> getMediaValoraciones(){
		Integer valLimp = getMediaValoracionTipo(TipoCategoria.Limpieza);
		Integer valJard = getMediaValoracionTipo(TipoCategoria.Jardineria);
		Integer valCrist = getMediaValoracionTipo(TipoCategoria.Cristaleria);
		Integer valMant = getMediaValoracionTipo(TipoCategoria.Mantenimiento);
		Map<TipoCategoria, Integer> l = new HashMap<TipoCategoria, Integer>();
		l.put(TipoCategoria.Limpieza, valLimp);
		l.put(TipoCategoria.Jardineria, valJard);
		l.put(TipoCategoria.Cristaleria, valCrist);
		l.put(TipoCategoria.Mantenimiento, valMant);
		return l;
	}
	
	@Transactional(readOnly=true)
	public List<Integer> serviciosConValoraciones(){
		return valoracionRepo.serviciosConValoracion();
	}

}
