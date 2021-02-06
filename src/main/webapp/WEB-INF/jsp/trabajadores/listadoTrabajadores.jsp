<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="trabajadores">
    <h2>Trabajadores</h2>
	
	<a href="/users/newTrabajador">
		<button type="button" class="btn btn-default btn-lg">
  			<span class="glyphicon glyphicon-plus" aria-hidden="true"></span> Nuevo trabajador
		</button>
	</a>
	<a href="/users/newAdministrador">
		<button type="button" class="btn btn-default btn-lg">
  			<span class="glyphicon glyphicon-plus" aria-hidden="true"></span> Nuevo administrador
		</button>
	</a>
	
	<a href="/horarios">
		<button type="button" class="btn btn-default btn-lg">
  			<span class="glyphicon glyphicon-plus" aria-hidden="true"></span> Horarios Trabajadores
		</button>
	</a>
	
    <table id="eventsTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;">NOMBRE</th>
            <th style="width: 150px;">APELLIDOS</th>
            <th style="width: 150px;">DNI</th>
            <th style="width: 150px;">DIRECCIÓN</th>
            <th style="width: 150px;">CORREO</th>
            <th style="width: 150px;">TELEFONO</th>
            <th style="width: 150px;">CATEGORIA</th>
            <th style="width: 150px;">CONTRATO</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${trabajadores}" var="trabajador">
            <tr>
                <td>
                    <c:out value="${trabajador.nombre}"/>
                </td>
                <td>
                    <c:out value="${trabajador.apellidos}"/>
                </td>
                 <td>
                    <c:out value="${trabajador.dni}"/>
                </td>
                <td>
                    <c:out value="${trabajador.direccion}"/>
                </td>
                <td>
                    <c:out value="${trabajador.correo}"/>
                </td>
                <td>
                    <c:out value="${trabajador.telefono}"/>
                </td>
                <td>
                    <c:out value="${trabajador.tipocategoria}"/>
                </td>
                
          		<td>
                	<c:forEach var="contrato" items="${trabajador.contratos}">
                			<c:out value="${contrato.id}"/>
                  	</c:forEach> 
                </td> 
                <td>
                	<spring:url value="/trabajadores/delete/{trabajadorId}" var="trabajadorUrl">
                		<spring:param name="trabajadorId" value="${trabajador.id}"/>
                	</spring:url>
                	<a href="${fn:escapeXml(trabajadorUrl)}">Delete</a>
                </td>
                <td>
                	<spring:url value="/trabajadores/{trabajadorId}/edit" var="trabajadorUrl">
                		<spring:param name="trabajadorId" value="${trabajador.id}"/>
                	</spring:url>
                	<a href="${fn:escapeXml(trabajadorUrl)}">Editar</a>
                </td>
                <td>
                	<spring:url value="contratosTrabajadores/{tId}/new" var="trabajadorUrl">
                		<spring:param name="tId" value="${trabajador.id}"/>
                	</spring:url>
                	<a href="${fn:escapeXml(trabajadorUrl)}">Nuevo Contrato</a>
                </td>
                <td>
                	<spring:url value="/servicios/trabajadores/{trabajadorId}" var="serviciosUrl">
                		<spring:param name="trabajadorId" value="${trabajador.id}"/>
                	</spring:url>
                	<a href="${fn:escapeXml(serviciosUrl)}">Ver servicios en los que trabaja</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>

