package org.springframework.samples.petclinic.model;

import javax.validation.constraints.NotBlank;

import org.springframework.samples.petclinic.customvalidators.ConfirmPassword;
import org.springframework.samples.petclinic.customvalidators.PasswordConstraint;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ConfirmPassword
public class UserForm {
	@NotBlank
	String username;
	
	@PasswordConstraint
	String password;
	
	@NotBlank
	String retypePassword;

}
