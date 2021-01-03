<<<<<<< HEAD
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="horarios">
    <h2>Horarios</h2>
	
	<a href="horarios/new">
		<button type="button" class="btn btn-default btn-lg">
  			<span class="glyphicon glyphicon-plus" aria-hidden="true"></span> Nuevo Horario
		</button>
	</a>
	
    <table id="horariosTable" class="table table-striped">
        <thead>
        <tr>
        	<th style="width: 150px;">NOMBRE_TRABAJADOR</th>
            <th style="width: 150px;">HORA_INICIO</th>
            <th style="width: 150px;">HORA_FIN</th>
            <th style="width: 150px;">DESCRIPCION</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${horarios}" var="horario">
            <tr>
            	<td>
                    <c:out value="${horario.trabajador.nombre}"/>
                </td>
                <td>
                    <c:out value="${horario.horaInicio}"/>
                </td>
                <td>
                    <c:out value="${horario.horaFin}"/>
                </td>
                <td>
                    <c:out value="${horario.descripcion}"/>
                </td>
                <td>
                	<spring:url value="/horarios/delete/{horarioId}" var="horarioUrl">
                		<spring:param name="horarioId" value="${horario.id}"/>
                	</spring:url>
                	<a href="${fn:escapeXml(horarioUrl)}">Delete</a>
                </td>
                <td>
                	<spring:url value="/horario/{horarioId}/edit" var="horarioUrl">
                		<spring:param name="horarioId" value="${horario.id}"/>
                	</spring:url>
                	<a href="${fn:escapeXml(horarioUrl)}">Editar</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>