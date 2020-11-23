<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="ofertas">
    <h2>Ofertas</h2>

    <table id="ofertasTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;">ID</th>
            <th style="width: 150px;">Producto</th>
            <th style="width: 150px;">Precio por unidad</th>
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
                
                <%--<td>
                	<spring:url value="/proveedores/delete/{provName}" var="provUrl">
                		<spring:param name="provName" value="${proveedor.name}"/>
                	</spring:url>
                	<a href="${fn:escapeXml(provUrl)}">Delete</a>
                </td>--%>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    
    
</petclinic:layout>