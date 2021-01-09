<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="reclamaciones">
    <h2>Reclamaciones</h2>

    <table id="reclamacionesTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;">Fecha</th>
            <th style="width: 200px;">Descripon</th>
            <th>ID Cliente</th>
            <th>ID Servicio</th> 
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${reclamaciones}" var="reclamacion">
            <tr>
                <td>
                    <c:out value="${reclamacion.fecha}"/>
                </td>
                <td>
                    <c:out value="${reclamacion.descripcion}"/>
                </td>
                <td>
                    <c:out value="${reclamacion.cliente.id}"/>
                </td>
                <td>
                    <c:out value="${reclamacion.servicio.id}"/>
                </td>
                <td>
                	<spring:url value="/reclamaciones/delete/{reclamacionId}" var="reclamacionUrl">
                		<spring:param name="reclamacionId" value="${reclamacion.id}"/>
                	</spring:url>
                	<a href="${fn:escapeXml(reclamacionUrl)}">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>