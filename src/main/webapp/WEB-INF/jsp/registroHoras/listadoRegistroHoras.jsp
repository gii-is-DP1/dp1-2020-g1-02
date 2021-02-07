<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="registro_horas">
    <h2>Registro de Horas</h2>
    

    <table id="registro_horastable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 180px;">Hora Entrada</th>
            <th style="width: 180px;">Hora Salida</th>
            <th style="width: 200px;">Nombre</th>
            <th style="width: 200px;">Apellido</th>
            <th> </th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${registro_horas}" var="registro_hora">
            <tr>
                <td>
                	<fmt:parseDate value="${registro_hora.hora_entrada}" pattern="yyyy-MM-dd'T'HH:mm" var="date"/>
					<fmt:formatDate value="${date}" type = "both" dateStyle="long" timeStyle="short" />
                </td>
                <td>
                	<fmt:parseDate value="${registro_hora.hora_salida}" pattern="yyyy-MM-dd'T'HH:mm" var="date"/>
					<fmt:formatDate value="${date}" type = "both" dateStyle="long" timeStyle="short" />
                </td>
                 <td>
                    <c:out value="${registro_hora.trabajador.nombre}"/>
                </td>
                <td>
                    <c:out value="${registro_hora.trabajador.apellidos}"/>
                </td>
                <td>
                	<spring:url value="/registroHoras/delete/{registroHorasId}" var="registroHorasUrl">
                		<spring:param name="registroHorasId" value="${registro_hora.id}"/>
                	</spring:url>
                	<a href="${fn:escapeXml(registroHorasUrl)}">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>