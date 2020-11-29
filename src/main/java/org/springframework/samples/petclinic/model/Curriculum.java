package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.sun.istack.NotNull;

import lombok.Data;

@Data
@Entity
@Table(name="curriculum")
public class Curriculum extends NamedEntity {
	
	@Column(name="tipocategoria")
	@NotNull
	private TipoCategoria tipocategoria;

	@Column(name="id_trab")
	@NotEmpty
	private Integer id_trab;
	
}
