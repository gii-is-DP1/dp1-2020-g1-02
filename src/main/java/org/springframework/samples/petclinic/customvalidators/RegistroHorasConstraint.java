package org.springframework.samples.petclinic.customvalidators;

import java.lang.annotation.ElementType;  
import java.lang.annotation.Retention;  
import java.lang.annotation.RetentionPolicy;  
import java.lang.annotation.Target;  
  
import javax.validation.Constraint;  
import javax.validation.Payload;  

@Constraint(validatedBy = RegistroHorasValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD , ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface RegistroHorasConstraint {
    String message() default "La hora de inicio tiene que ser antes que la hora de fin";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}