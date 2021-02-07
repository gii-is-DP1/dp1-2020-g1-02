<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<spring:url value="/resources/images/delete.jpg" var="delete"/>

<petclinic:layout pageName="ofertas">
    <h2>Ofertas</h2>

    <table id="ofertasTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;">ID</th>
            <th style="width: 150px;">Producto</th>
            <th style="width: 200px;">Precio por unidad</th>
            <th style="width: 150px;">Proveedor</th>
            <%-- <th style="width: 150px;">Cantidad deseada</th>--%>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${ofertas}" var="oferta">
            <tr>
                <td>
                    <c:out value="${oferta.id}"/>
                </td>
                <td>
                    <c:out value="${oferta.name}"/>
                </td>
                 <td>
                    <c:out value="${oferta.precioU}"/>
                </td>
                <td>
                    <c:out value="${oferta.proveedor.name}"/>
                </td>
                <td>
                	<spring:url value="pedidos/new/{oId}" var="addUrl">
                        <spring:param name="oId" value="${oferta.id}"/>
                        </spring:url>
                        <a href="${fn:escapeXml(addUrl)}" class="btn btn-default">Crear pedido</a>
                </td>
                <td>
                	<spring:url value="/ofertas/delete/{ofertaId}" var="productUrl">
                		<spring:param name="ofertaId" value="${oferta.id}"/>
                	</spring:url>
                	<a href="${fn:escapeXml(productUrl)}" class="btn btn-default"><img src="${delete}" width="30px"/></a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    
    
</petclinic:layout>