package org.springframework.samples.petclinic.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import org.junit.jupiter.api.Test;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.samples.petclinic.customvalidators.FechaValidatorServicioConstraint;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;


public class ContratoTest {
	
	private Validator createValidator() {
		LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
		localValidatorFactoryBean.afterPropertiesSet();
		return localValidatorFactoryBean;
	}
	
	@Test
	public void ContratoCorrecto() {
		Contrato contrato = new Contrato();
		
		contrato.setFechainicial(LocalDate.now());
		contrato.setFechafinal(LocalDate.of(2030, 12, 31));

		Validator validator = createValidator();
		Set<ConstraintViolation<Contrato>> constraintViolations = validator.validate(contrato);

		assertThat(constraintViolations.size()).isEqualTo(0);
		
	}
	
	
	@Test
	public void ContratoFechaFinAntesFechaInicio() {
		Contrato contrato = new Contrato();
		
		contrato.setFechafinal(LocalDate.now());
		contrato.setFechainicial(LocalDate.of(2030, 12, 31));;

		Validator validator = createValidator();
		Set<ConstraintViolation<Contrato>> constraintViolations = validator.validate(contrato);

		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Contrato> violation = constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath().toString()).isEqualTo("");
		assertThat(violation.getMessage()).isEqualTo("La fecha de inicio tiene que ser antes que la fecha de fin");
	}
}
