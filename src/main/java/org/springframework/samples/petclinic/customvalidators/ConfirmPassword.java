package org.springframework.samples.petclinic.customvalidators;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = ConfirmPasswordValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ConfirmPassword {
    String message() default "Las contraseñas no coinciden";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

