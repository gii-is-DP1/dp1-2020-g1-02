<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<spring:url value="/resources/images/delete.jpg" var="delete"/>

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
                	<spring:url value="/trabajadores/{trabajadorId}/edit" var="trabajadorUrl">
                		<spring:param name="trabajadorId" value="${trabajador.id}"/>
                	</spring:url>
                	<a href="${fn:escapeXml(trabajadorUrl)}">Editar</a>
                </td>
                	<td>
                	<spring:url value="/contratosTrabajadores/{trabajadorId}" var="contratosUrl">
                		<spring:param name="trabajadorId" value="${trabajador.id}"/>
                	</spring:url>
                	<a href="${fn:escapeXml(contratosUrl)}">Contratos</a>
                </td> 
               
                <td>
                	<spring:url value="/servicios/trabajadores/{trabajadorId}" var="serviciosUrl">
                		<spring:param name="trabajadorId" value="${trabajador.id}"/>
                	</spring:url>
                	<a href="${fn:escapeXml(serviciosUrl)}">Servicios</a>
                </td>
                
                 <td>
                	<spring:url value="/horarios/{trabajadorId}" var="horariosUrl">
                		<spring:param name="trabajadorId" value="${trabajador.id}"/>
                	</spring:url>
                	<a href="${fn:escapeXml(horariosUrl)}">Horarios</a>
                </td>
                
                  <td>
                	<spring:url value="/trabajadores/delete/{trabajadorId}" var="trabajadorUrl">
                		<spring:param name="trabajadorId" value="${trabajador.id}"/>
                	</spring:url>
                	<a href="${fn:escapeXml(trabajadorUrl)}"><img src="${delete}" width="30px"/></a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>

