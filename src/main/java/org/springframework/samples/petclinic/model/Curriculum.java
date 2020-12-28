package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Entity
@Table(name="curriculum")
public class Curriculum extends BaseEntity {
	
	@Column(name = "nombre")
    @NotEmpty
	private String nombre;
	
	@Column(name="tipocategoria")
	@NotEmpty
	private TipoCategoria tipocategoria;

	@Column(name="id_trab")
	@NotEmpty
	private Integer id_trab;
	
	@OneToOne
    //@JoinColumn(name="trabajador_id")
    private Trabajador trabajador;
	
}
