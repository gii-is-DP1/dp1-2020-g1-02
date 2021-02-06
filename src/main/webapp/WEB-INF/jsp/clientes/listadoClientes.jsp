<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<spring:url value="/resources/images/delete.jpg" var="delete"/>

<petclinic:layout pageName="clientes">
    <h2>Clientes</h2>

    <table id="clientesTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;">Nombre</th>
            <th style="width: 200px;">Apellidos</th>
            <th>Telefono</th>
            <th>Direccion</th>
            <th>Dni</th>
            <th>Correo</th>
            <th style="width: 150px;">Instalaciones</th>  
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${cliente}" var="cliente">
            <tr>
                <td>
                    <c:out value="${cliente.nombre}"/>
                </td>
                <td>
                    <c:out value="${cliente.apellidos}"/>
                </td>
                <td>
                    <c:out value="${cliente.telefono}"/>
                </td>
                <td>
                    <c:out value="${cliente.direccion}"/>
                </td>
                <td>
                    <c:out value="${cliente.dni}"/>
                </td>
                 <td>
                    <c:out value="${cliente.correo}"/>
                </td>
                <td>
                	<c:forEach var="instalacion" items="${cliente.instalaciones}">
                			<c:out value="${instalacion.id}"/>
                  	</c:forEach> 
                </td> 
                <td>
                	<spring:url value="/clientes/delete/{clienteId}" var="clienteUrl">
                		<spring:param name="clienteId" value="${cliente.id}"/>
                	</spring:url>
                	<a href="${fn:escapeXml(clienteUrl)}"><img src="${delete}" width="30px"/></a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>