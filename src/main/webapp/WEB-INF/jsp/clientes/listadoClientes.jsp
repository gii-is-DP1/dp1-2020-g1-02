<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<spring:url value="/resources/images/delete.png" var="delete"/>

<petclinic:layout pageName="clientes">
    <h2>Clientes</h2>

    <table id="clientesTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 11%;">Nombre</th>
            <th style="width: 11%;">Apellidos</th>
            <th style="width: 11%;">Telefono</th>
            <th style="width: 11%;">Direccion</th>
            <th style="width: 11%;">Dni</th>
            <th style="width: 11%;">Correo</th> 
            <th style="width: 3%;"> </th>
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