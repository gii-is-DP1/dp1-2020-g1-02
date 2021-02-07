package org.springframework.samples.petclinic.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User{ 
	@Id
	@NotBlank
	String username;

	String password;
	
	@Transient 
	private String retypePassword;
	 
	
	boolean enabled;
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
	private Authorities authorities;
	
	@ManyToMany
	@JoinTable(name = "aux", 
			  joinColumns = @JoinColumn(name = "username"), 
			  inverseJoinColumns = @JoinColumn(name = "mensaje_id"))
    private List<Mensaje> mensajesRecibidos;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="emisor")
    private List<Mensaje> mensajesEnviados;

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
