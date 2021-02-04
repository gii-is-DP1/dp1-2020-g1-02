<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="valoraciones">
    <h2>Valoraciones</h2>
    
    <table id="valoracionesTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;">Fecha</th>
            <th style="width: 200px;">Nivel de Satisfaccion</th>
            <th>ID Cliente</th>
            <th>ID Servicio</th> 
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${valoraciones}" var="valoracion">
            <tr>
                <td>
                    <c:out value="${valoracion.fecha}"/>
                </td>
                <td>
                    <c:out value="${valoracion.nivelsatisfaccion}"/>
                </td>
                <td>
                    <c:out value="${valoracion.cliente.id}"/>
                </td>
                <td>
                    <c:out value="${valoracion.servicio.id}"/>
                </td>
                <td>
                	<spring:url value="/valoraciones/delete/{valoracionId}" var="valoracionUrl">
                		<spring:param name="valoracionId" value="${valoracion.id}"/>
                	</spring:url>
                	<a href="${fn:escapeXml(valoracionUrl)}">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>v