package org.springframework.samples.petclinic.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Locale;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import org.junit.jupiter.api.Test;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.samples.petclinic.customvalidators.FechaValidatorServicioConstraint;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;


public class HorarioTest {
	
	private Validator createValidator() {
		LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
		localValidatorFactoryBean.afterPropertiesSet();
		return localValidatorFactoryBean;
	}
	
	@Test
	public void ContratoCorrecto() {
		Horario horario = new Horario();
		
		horario.setFecha(LocalDate.now());
		horario.setHora_inicio(LocalTime.of(13, 30));
		horario.setHora_fin(LocalTime.of(15, 30));
		horario.setDescripcion("A limpiar");

		Validator validator = createValidator();
		Set<ConstraintViolation<Horario>> constraintViolations = validator.validate(horario);

		assertThat(constraintViolations.size()).isEqualTo(0);
		
	}
	
	
	@Test
	public void ContratoFechaFinAntesFechaInicio() {
		Horario horario = new Horario();
		
		horario.setFecha(LocalDate.now());
		horario.setHora_fin(LocalTime.of(13, 30));
		horario.setHora_inicio(LocalTime.of(15, 30));
		horario.setDescripcion("A limpiar");

		Validator validator = createValidator();
		Set<ConstraintViolation<Horario>> constraintViolations = validator.validate(horario);

		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Horario> violation = constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath().toString()).isEqualTo("");
		assertThat(violation.getMessage()).isEqualTo("La hora de inicio tiene que ser antes que la hora de fin");
	}
}
