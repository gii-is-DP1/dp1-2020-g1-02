package org.springframework.samples.petclinic.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name="trabajador")
public class Trabajador extends PersonaEntity {

    @NotNull
    private TipoCategoria tipocategoria;
    
    @OneToMany(cascade=CascadeType.ALL, mappedBy="trabajador")
    private Set<ContratoTrabajador> contratos;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy="trabajador")
    private Set<Horario> horarios;
    
    @OneToOne(optional=true)
    private Curriculum curriculum;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy="trabajador")
    private Set<RegistroHoras> registroHoras;

	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "username", referencedColumnName = "username")
	  private User user;
	
	  public User getUser() {
		  return user;
	  }

}