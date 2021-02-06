package org.springframework.samples.petclinic.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
	
	@NotNull
	private String lugar;
	
	@NotNull
	private TipoCategoria tipocategoria;
	
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@NotNull
	private LocalDate fechainicio;
	
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@NotNull
	private LocalDate fechafin;
	
	@NotNull
	private EstadoServicio estado;
	
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="cliente_id")
    private Cliente cliente;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="servicio")
    private List<Presupuesto> presupuestos;
	
    @OneToMany(cascade=CascadeType.ALL, mappedBy="servicio")
    private List<Reclamacion> reclamaciones;
    
    @OneToMany(cascade=CascadeType.ALL, mappedBy="servicio")
    private List<Valoracion> valoraciones;


}
