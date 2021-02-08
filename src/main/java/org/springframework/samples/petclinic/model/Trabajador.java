package org.springframework.samples.petclinic.model;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.samples.petclinic.customvalidators.ConfirmPassword;
import org.springframework.samples.petclinic.customvalidators.PasswordConstraint;

import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name="trabajador")
public class Trabajador extends PersonaEntity {

    @NotNull
    private TipoCategoria tipocategoria;
    
    @OneToMany(cascade=CascadeType.REMOVE, mappedBy="trabajador")
    private Set<ContratoTrabajador> contratos;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy="trabajador")
    private Set<Horario> horarios;
    
    @OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="curriculum")
    private Curriculum curriculum;
    
    
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy="trabajador")
    private Set<RegistroHoras> registroHoras;

	@OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "username", referencedColumnName = "username")
	@PasswordConstraint
	@ConfirmPassword
	  private User user;
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "aux_ts", 
			  joinColumns = @JoinColumn(name = "trabajador_id"), 
			  inverseJoinColumns = @JoinColumn(name = "servicio_id"))
    private List<Servicio> servicios;
	
	  public User getUser() {
		  return user;
	  }
	 

}