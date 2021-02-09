package org.springframework.samples.petclinic.customvalidators;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = FechaInicioValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface FechaInicioConstraint {
	String message() default "La fecha inicial no puede ser posterios al día de hoy";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
