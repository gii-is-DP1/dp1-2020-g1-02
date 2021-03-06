<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="productos">
    <h2>Productos</h2>
    
    <spring:url value="/productos/new" var="productUrl">
    </spring:url>
    <a href="${fn:escapeXml(productUrl)}" class="btn btn-default">A�adir Producto</a>
    
     <spring:url value="/ofertas" var="ofertasUrl">
    </spring:url>
    <a href="${fn:escapeXml(ofertasUrl)}" class="btn btn-default">Ver Ofertas</a>
    
     <spring:url value="/pedidos" var="pedidosUrl">
    </spring:url>
    <a href="${fn:escapeXml(pedidosUrl)}" class="btn btn-default">Ver Pedidos</a>

    <table id="productosTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 35%;">Nombre</th>
            <th style="width: 35%;">Cantidad en Stock</th>
            <th style="width: 10%;"></th>
            <th style="width: 10%;"></th>
            <th style="width: 10%;"></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${producto}" var="producto">
            <tr>
                <td>
                    <c:out value="${producto.name}"/>
                </td>
                <td>
                    <c:out value="${producto.cantidad}"/>
                </td>
                <td>
                	<spring:url value="/productos/{productId}/sumar" var="productUrl">
                		<spring:param name="productId" value="${producto.id}"/>
                	</spring:url>
                	<a href="${fn:escapeXml(productUrl)}"><img src="/resources/images/sumar.png" width="20px"/></a>
                </td>
                <td>
                	<spring:url value="/productos/{productId}/restar" var="productUrl">
                		<spring:param name="productId" value="${producto.id}"/>
                	</spring:url>
                	<a href="${fn:escapeXml(productUrl)}"><img src="/resources/images/restar.png" width="20px"/></a>
                </td>
                <td>
                	<spring:url value="/productos/delete/{productoId}" var="productUrl">
                		<spring:param name="productoId" value="${producto.id}"/>
                	</spring:url>
                	<a href="${fn:escapeXml(productUrl)}"><img src="/resources/images/delete.png" width="30px"/></a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>