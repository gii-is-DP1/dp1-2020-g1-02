<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="instalaciones">
    <h2>Instalaciones</h2>
    
    <a href="instalaciones/new">
		<button type="button" class="btn btn-default btn-lg">
  			<span class="glyphicon glyphicon-plus" aria-hidden="true"></span> Nueva Instalacion
		</button>
	</a>

    <table id="instalacionesTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;">Lugar</th>
            <th style="width: 200px;">Dimension</th>
            <th>ID Cliente</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${instalacion}" var="instalacion">
            <tr>
                <td>
                    <c:out value="${instalacion.lugar}"/>
                </td>
                <td>
                    <c:out value="${instalacion.dimension}"/>
                </td>
                <td>
                    <c:out value="${instalacion.cliente.id}"/>
                </td>
                <td>
                	<spring:url value="/instalaciones/delete/{instalacionId}" var="instalacionUrl">
                		<spring:param name="instalacionId" value="${instalacion.id}"/>
                	</spring:url>
                	<a href="${fn:escapeXml(instalacionUrl)}">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>