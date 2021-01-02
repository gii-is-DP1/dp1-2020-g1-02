package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="curriculum")
public class Curriculum extends BaseEntity {
	
	@Column(name = "nombre")
    @NotNull
	private String nombre;
	
	@Column(name="tipocategoria")
	@NotNull
	private TipoCategoria tipocategoria;
	
	@OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="trabajador_id")
    private Trabajador trabajador;
	
}
