package org.springframework.samples.petclinic.customvalidators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements 
ConstraintValidator<PasswordConstraint, String> {

  @Override
  public void initialize(PasswordConstraint psw) {
  }

  @Override
  public boolean isValid(String psw, ConstraintValidatorContext cxt) {
      return psw != null && psw.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$")
    		  && (psw.length() > 8);
  }

}
