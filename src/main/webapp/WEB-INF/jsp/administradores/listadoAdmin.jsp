<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="administradores">
    <h2>Administradores</h2>

    <table id="administradoresTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;">Nombre</th>
            <th style="width: 200px;">Apellidos</th>
            <th style="width: 200px;">Categoria</th>
         
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${administrador}" var="administrador">
            <tr>
                <td>
                    <c:out value="${administrador.nombre}"/>
                </td>
                <td>
                    <c:out value="${administrador.apellidos}"/>
                </td>
                <td>
                    <c:out value="${administrador.tipocategoria}"/>
                </td>
                <td>
                	<spring:url value="/administradores/delete/{adminId}" var="adminUrl">
                		<spring:param name="adminId" value="${administrador.id}"/>
                	</spring:url>
                	<a href="${fn:escapeXml(adminUrl)}">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>