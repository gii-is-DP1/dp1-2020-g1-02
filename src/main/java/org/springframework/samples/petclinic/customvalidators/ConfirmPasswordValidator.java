package org.springframework.samples.petclinic.customvalidators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.samples.petclinic.model.UserForm;

public class ConfirmPasswordValidator implements 
ConstraintValidator<ConfirmPassword, UserForm> {

  @Override
  public void initialize(ConfirmPassword psw) {
  }

  @Override
  public boolean isValid(UserForm user, ConstraintValidatorContext cxt) {
      return user.getRetypePassword() != null && user.getPassword()!= null 
    		  && user.getPassword().equals(user.getRetypePassword());
  }
}
