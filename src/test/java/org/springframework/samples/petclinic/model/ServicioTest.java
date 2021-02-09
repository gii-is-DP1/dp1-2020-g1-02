package org.springframework.samples.petclinic.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.jupiter.api.Test;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

public class ServicioTest {
	
	private Validator createValidator() {
		LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
		localValidatorFactoryBean.afterPropertiesSet();
		return localValidatorFactoryBean;
	}
	
	@Test
	public void ServicioCorrecto() {
		Servicio servicio = new Servicio();
		servicio.setLugar("casa");
		servicio.setTipocategoria(TipoCategoria.Cristaleria);
		servicio.setFechainicio(LocalDate.now());
		servicio.setFechafin(LocalDate.of(2030, 12, 31));
		servicio.setEstado(EstadoServicio.Espera);

		Validator validator = createValidator();
		Set<ConstraintViolation<Servicio>> constraintViolations = validator.validate(servicio);

		assertThat(constraintViolations.size()).isEqualTo(0);
		
	}
	
	@Test
	public void ServicioFechaFinAntesFechaInicio() {
		Servicio servicio = new Servicio();
		servicio.setLugar("casa");
		servicio.setTipocategoria(TipoCategoria.Cristaleria);
		servicio.setFechafin(LocalDate.now());
		servicio.setFechainicio(LocalDate.of(2030, 12, 31));
		servicio.setEstado(EstadoServicio.Espera);

		Validator validator = createValidator();
		Set<ConstraintViolation<Servicio>> constraintViolations = validator.validate(servicio);

		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Servicio> violation = constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath().toString()).isEqualTo("");
		assertThat(violation.getMessage()).isEqualTo("La fecha de inicio tiene que ser antes que la fecha");
	}
}
