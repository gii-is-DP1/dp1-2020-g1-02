package org.springframework.samples.petclinic.customvalidators;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = MensajeValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD , ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface MensajeConstraint {
    String message() default "Tiene que haber mínimo un receptor";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}