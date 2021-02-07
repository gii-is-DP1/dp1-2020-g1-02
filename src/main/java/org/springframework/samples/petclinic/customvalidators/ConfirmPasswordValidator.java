package org.springframework.samples.petclinic.customvalidators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.samples.petclinic.model.User;

public class ConfirmPasswordValidator implements 
ConstraintValidator<ConfirmPassword, User> {

  @Override
  public void initialize(ConfirmPassword psw) {
  }

  @Override
  public boolean isValid(User user, ConstraintValidatorContext cxt) {
      return user.getPassword().equals(user.getRetypePassword());
  }

}
