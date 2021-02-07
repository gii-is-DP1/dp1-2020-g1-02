package org.springframework.samples.petclinic.customvalidators;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = DNIValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface DNIConstraint {
    String message() default "El DNI introducido no es válido";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

