<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="pedidos">
    <h2>Pedidos</h2>
	<a href="//facturas">
		<button type="button" class="btn btn-default btn-lg">
  			<span class="glyphicon glyphicon-plus" aria-hidden="true"></span> Ver Facturas
		</button>
	</a>
    <table id="pedidosTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;">Fecha</th>
            <th style="width: 150px;">Cantidad del producto</th>
            <th style="width: 150px;">Nombre del producto</th>
            <th style="width: 150px;"></th>
        </tr>
            
        </thead>
        <tbody>
        <c:forEach items="${pedidos}" var="pedido">
            <tr>
                <td>
                    <c:out value="${pedido.fechaPedido}"/>
                </td>
                <td>
                    <c:out value="${pedido.cantidadProducto}"/>
                </td>
                <td>
                    <c:out value="${pedido.oferta.producto.name}"/>
                </td>
                
                <td>
                
                    <spring:url value="/facturas/{facturaId}" var="facturaUrl">
              		<spring:param name="facturaId" value="${pedido.factura.id}"/>
	              	</spring:url>
	              	<a href="${fn:escapeXml(facturaUrl)}">Ver factura</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>