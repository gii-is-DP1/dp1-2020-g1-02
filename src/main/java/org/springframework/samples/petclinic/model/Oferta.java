package org.springframework.samples.petclinic.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="oferta")
public class Oferta extends NamedEntity {
	
	@NotNull
	@Range(min=0, max=100)
	private Double precioU;
	
	@ManyToOne
    @JoinColumn(name="producto_id")
    private Producto producto;
	
	@ManyToOne
	@JoinColumn(name="proveedor")
	@NotNull
	private Proveedor proveedor;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="oferta")
    private List<Pedido> pedidos;
	
	public void añadirPedido(Pedido pedido) {
		this.pedidos.add(pedido);
	}
	
}