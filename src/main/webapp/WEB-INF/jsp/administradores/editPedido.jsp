<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>


    
<petclinic:layout pageName="Pedidos">
 <jsp:attribute name="customScript">
    </jsp:attribute>
    <jsp:body>
    
    <h2>Oferta seleccionada</h2>
        <table id="ofertasTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;">ID</th>
            <th style="width: 150px;">Producto</th>
            <th style="width: 150px;">Precio por unidad</th>
            <th style="width: 150px;">Proveedor</th>
            <%-- <th style="width: 150px;">Cantidad deseada</th>--%>
        </tr>
        </thead>
        <tbody>
            <tr>
                <td>
                    <c:out value="${pedido.oferta.id}"/>
                </td>
                <td>
                    <c:out value="${pedido.oferta.name}"/>
                </td>
                 <td>
                    <c:out value="${pedido.oferta.precioU}"/>
                </td>
                <td>
                    <c:out value="${pedido.oferta.proveedor.name}"/>
                </td>
            </tr>
        </tbody>
    </table>
       <h2>Pedido</h2>
       <form:form modelAttribute="pedido" class="form-horizontal" action="/pedidos/save">
       		<div class="form-group has-feedback">
       			<%-- Fecha del pedido: <c:out value="${pedido.fechaPedido}"/> --%>
       			<!-- <input label="Fecha del pedido" name="fechaPedido" value=' <c:out value="${pedido.fechaPedido}"/>' disabled/>-->
       			<petclinic:inputField label="Cantidad" name="cantidadProducto"/>
       			
 
				<petclinic:inputField label="Fecha del pedido" name="fechaPedido" />
		       	<%-- <input type="text" id="fecha" name="fechaPedido" value=' <c:out value="${pedido.fechaPedido}"/>'> --%>
	       		<input type="hidden" id="oferta" name="oferta" value='<c:out value="${pedido.oferta.id}"/>'>
  				
       		</div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button class="btn btn-default" type="submit">Guardar Pedido</button>
                </div>
            </div>
        </form:form>
        
        <h3><c:out value="${error}"/></h3>
        
    </jsp:body>
    
    

</petclinic:layout>
	