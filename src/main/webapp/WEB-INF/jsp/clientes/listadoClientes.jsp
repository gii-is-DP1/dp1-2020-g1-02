<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

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
            <th>Email</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${clientes}" var="cliente">
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
                    <c:out value="${cliente.email}"/>
                </td>
                <td>
                	<spring:url value="/clientes/delete/{clienteDNI}" var="clienteUrl">
                		<spring:param name="clienteDNI" value="${cliente.dni}"/>
                	</spring:url>
                	<a href="${fn:escapeXml(clienteUrl)}">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>