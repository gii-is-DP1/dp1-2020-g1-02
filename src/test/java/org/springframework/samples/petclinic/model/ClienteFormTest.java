package org.springframework.samples.petclinic.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import org.junit.jupiter.api.Test;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

public class ClienteFormTest {
	
	private Validator createValidator() {
		LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
		localValidatorFactoryBean.afterPropertiesSet();
		return localValidatorFactoryBean;
	}
	
	@Test
	public void FormCorrectoTest() {
		ClienteForm formTest = new ClienteForm();
		
		formTest.setNombre("Carlos Jesus");
		formTest.setApellidos("Villadiego García");
		formTest.setTelefono("666666666");
		formTest.setCorreo("carvilgar1@alum.es");
		formTest.setDni("21150498C");
		formTest.setDireccion("C/ Sobressaliente, 10");
		formTest.setUsername("carvilgar1");
		formTest.setPassword("aaaaaaaaA1");
		formTest.setRetypePassword("aaaaaaaaA1");
		
		
		
		Validator validator = createValidator();
		Set<ConstraintViolation<ClienteForm>> constraintViolations = validator.validate(formTest);

		assertThat(constraintViolations.size()).isEqualTo(0);
		 
	}
	
	@Test
	public void FormDNIErroneo() {
		ClienteForm formTest = new ClienteForm();
		
		formTest.setNombre("Carlos Jesus");
		formTest.setApellidos("Villadiego García");
		formTest.setTelefono("666666666");
		formTest.setCorreo("carvilgar1@alum.es");
		formTest.setDni("21150498A");
		formTest.setDireccion("C/ Sobressaliente, 10");
		formTest.setUsername("carvilgar1");
		formTest.setPassword("aaaaaaaaA1");
		formTest.setRetypePassword("aaaaaaaaA1");
		
		
		
		Validator validator = createValidator();
		Set<ConstraintViolation<ClienteForm>> constraintViolations = validator.validate(formTest);

		assertThat(constraintViolations.size()).isEqualTo(1);
			
		ConstraintViolation<ClienteForm> violation = constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath().toString()).isEqualTo("dni");
		assertThat(violation.getMessage()).isEqualTo("El DNI introducido no es válido");
		 
	}
	
	@Test
	public void FormCorreoErroneo() {
		ClienteForm formTest = new ClienteForm();
		
		formTest.setNombre("Carlos Jesus");
		formTest.setApellidos("Villadiego García");
		formTest.setTelefono("666666666");
		formTest.setCorreo("carvilgar1");
		formTest.setDni("21150498C");
		formTest.setDireccion("C/ Sobressaliente, 10");
		formTest.setUsername("carvilgar1");
		formTest.setPassword("aaaaaaaaA1");
		formTest.setRetypePassword("aaaaaaaaA1");
		
		
		
		Validator validator = createValidator();
		Set<ConstraintViolation<ClienteForm>> constraintViolations = validator.validate(formTest);

		assertThat(constraintViolations.size()).isEqualTo(1);
			
		ConstraintViolation<ClienteForm> violation = constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath().toString()).isEqualTo("correo");
		 
	}
	
	
	@Test
	public void FormContrasenyaErroneo() {
		ClienteForm formTest = new ClienteForm();
		
		formTest.setNombre("Carlos Jesus");
		formTest.setApellidos("Villadiego García");
		formTest.setTelefono("666666666");
		formTest.setCorreo("carvilgar1@alum.es");
		formTest.setDni("21150498C");
		formTest.setDireccion("C/ Sobressaliente, 10");
		formTest.setUsername("carvilgar1");
		formTest.setPassword("123");
		formTest.setRetypePassword("123");
		
		
		
		Validator validator = createValidator();
		Set<ConstraintViolation<ClienteForm>> constraintViolations = validator.validate(formTest);

		assertThat(constraintViolations.size()).isEqualTo(1);
			
		ConstraintViolation<ClienteForm> violation = constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath().toString()).isEqualTo("password");
		assertThat(violation.getMessage()).isEqualTo("La contraseña no es correcta");
		 
	}
	
	@Test
	public void FormContrasenyaNoCoincide() {
ClienteForm formTest = new ClienteForm();
		
		formTest.setNombre("Carlos Jesus");
		formTest.setApellidos("Villadiego García");
		formTest.setTelefono("666666666");
		formTest.setCorreo("carvilgar1@alum.es");
		formTest.setDni("21150498C");
		formTest.setDireccion("C/ Sobressaliente, 10");
		formTest.setUsername("carvilgar1");
		formTest.setPassword("aaaaaaaaaaaaA1");
		formTest.setRetypePassword("123");
		
		
		
		Validator validator = createValidator();
		Set<ConstraintViolation<ClienteForm>> constraintViolations = validator.validate(formTest);

		assertThat(constraintViolations.size()).isEqualTo(1);
			
		ConstraintViolation<ClienteForm> violation = constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath().toString()).isEqualTo("");
		assertThat(violation.getMessage()).isEqualTo("Las contraseñas no coinciden"); 
	}
	
	@Test
	public void FormRetypeContrasenyaVacio() {
		ClienteForm formTest = new ClienteForm();
		
		formTest.setNombre("Carlos Jesus");
		formTest.setApellidos("Villadiego García");
		formTest.setTelefono("666666666");
		formTest.setCorreo("carvilgar1@alum.es");
		formTest.setDni("21150498C");
		formTest.setDireccion("C/ Sobressaliente, 10");
		formTest.setUsername("carvilgar1");
		formTest.setPassword("aaaaaaaaaaaA1");
		formTest.setRetypePassword("");
		
		
		
		Validator validator = createValidator();
		List<ConstraintViolation<ClienteForm>> constraintViolations = validator.validate(formTest).stream().collect(Collectors.toList());

		assertThat(constraintViolations.size()).isEqualTo(2);
			
		ConstraintViolation<ClienteForm> violation2 = constraintViolations.get(0);
		ConstraintViolation<ClienteForm> violation = constraintViolations.get(1);
		assertThat(violation.getPropertyPath().toString()).isEqualTo("");
		assertThat(violation.getMessage()).isEqualTo("Las contraseñas no coinciden");
		assertThat(violation2.getPropertyPath().toString()).isEqualTo("retypePassword");
	}


}
