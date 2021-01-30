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

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public Authorities getAuthorities() {
		return authorities;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public void setAuthorities(Authorities authorities) {
		this.authorities = authorities;
	}
}
