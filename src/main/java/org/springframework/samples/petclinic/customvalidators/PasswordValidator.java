package org.springframework.samples.petclinic.customvalidators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.samples.petclinic.model.User;

public class PasswordValidator implements 
ConstraintValidator<PasswordConstraint, User> {

  @Override
  public void initialize(PasswordConstraint psw) {
  }

  @Override
  public boolean isValid(User user, ConstraintValidatorContext cxt) {
      return user.getPassword() != null && user.getPassword().matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$")
    		  && (user.getPassword().length() > 8);
  }

}
