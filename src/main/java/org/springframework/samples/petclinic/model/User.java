package org.springframework.samples.petclinic.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User{
	@Id
	@NotBlank
	String username;
	
	String password;
	
	boolean enabled;
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
	private Authorities authorities;
}
