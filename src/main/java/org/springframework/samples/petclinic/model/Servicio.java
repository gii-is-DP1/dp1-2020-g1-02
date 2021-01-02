package org.springframework.samples.petclinic.model;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "servicio")
public class Servicio extends BaseEntity {
	
	@Column(name = "lugar")
	@NotNull
	private String lugar;
	
	@Column(name = "tipocategoria")
	@NotNull
	private TipoCategoria tipocategoria;
	
	@Column(name = "fechainicio")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@NotNull
	private LocalDate fechainicio;
	
	@Column(name = "fechafin")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@NotNull
	private LocalDate fechafin;
	
	@Column(name= "estado")
	@NotNull
	private EstadoServicio estado;
	
	@OneToOne(optional=true)
	@JoinColumn(name="contrato_id")
	private ContratoServicio contrato;
	
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="cliente_id")
    private Cliente cliente;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="servicio")
    private List<Presupuesto> presupuestos;
	
}
