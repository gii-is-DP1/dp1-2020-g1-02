package org.springframework.samples.petclinic.model;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Data
@Table(name="oferta")
public class Oferta extends NamedEntity {
	
	@NotEmpty
	private String precioU;
	
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