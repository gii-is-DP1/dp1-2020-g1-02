package org.springframework.samples.petclinic.customvalidators;

import java.lang.annotation.ElementType;  
import java.lang.annotation.Retention;  
import java.lang.annotation.RetentionPolicy;  
import java.lang.annotation.Target;  
  
import javax.validation.Constraint;  
import javax.validation.Payload;  

@Constraint(validatedBy = FechaValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD , ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface FechaValidatorConstraint {
    String message() default "La fecha de inicio tiene que ser antes que la fecha";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}