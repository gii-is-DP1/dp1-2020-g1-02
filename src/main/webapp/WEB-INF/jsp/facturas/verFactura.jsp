<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="facturas">
    <h2>Factura: <c:out value="${factura.id}"/></h2>
	<spring:url value="/facturas" var="facturaUrl">
    </spring:url>
    <a href="${fn:escapeXml(facturaUrl)}" class="btn btn-default">Ver todas las facturas</a>
    <table id="facturasTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 20%;">Fecha</th>
            <th style="width:  20%;">Precio total</th>
            <th style="width:  20%;">Nombre del proveedor</th>
            <th style="width:  20%;">ID del pedido</th>
           


        </tr>
        </thead>
        <tbody>
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
          </tr>
            
        </tbody>
    </table>
</petclinic:layout>