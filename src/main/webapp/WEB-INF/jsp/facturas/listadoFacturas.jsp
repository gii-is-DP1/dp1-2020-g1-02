<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="facturas">
    <h2>Facturas</h2>
    
    <h3> Filtrar proveedor: </h3>
    <form action="/facturas/filtrado/{nameProv}"> 
    	<input type="text"> 
    	<button type="submit"> Filtrar </button>
    	<spring:url value="/clientes/delete/{clienteId}" var="clienteUrl">
              <spring:param name="clienteId" value="${cliente.id}"/>
        </spring:url>
        <a href="${fn:escapeXml(clienteUrl)}">Delete</a>
    </form>

    <table id="facturasTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;">Fecha</th>
            <th style="width: 150px;">Precio total</th>
            <th style="width: 150px;">>Nombre del proveedor</th>
            <th style="width: 150px;">ID del pedido</th>


        </tr>
        </thead>
        <tbody>
        <c:forEach items="${facturas}" var="factura">
            <tr>
                <td>
                    <c:out value="${factura.fecha}"/>
                </td>
                <td>
                    <c:out value="${factura.precio_total}"/>
                </td>
                <td>
                    <c:out value="${factura.proveedor.name}"/>
                </td>
                <td>
                    <c:out value="${factura.pedido.id}"/>
                </td>
                
                <td>
                	<spring:url value="/facturas/delete/{facturaId}" var="facturaUrl">
                		<spring:param name="facturaId" value="${factura.id}"/>
                	</spring:url>
                	<a href="${fn:escapeXml(facturaUrl)}">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>