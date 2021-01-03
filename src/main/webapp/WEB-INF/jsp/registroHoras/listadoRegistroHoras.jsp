<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="registroHoras">
    <h2>Registro de Horas</h2>

    <table id="registroHorastable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;">Hora Entrada</th>
            <th style="width: 200px;">Hora Salida</th>
            <th style="width: 200px;">Nombre del Trabajador</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${registroHoras}" var="registroHoras">
            <tr>
                <td>
                    <c:out value="${registroHoras.horaEntrada}"/>
                </td>
                <td>
                    <c:out value="${registroHoras.horaSalidas}"/>
                </td>
                 <td>
                    <c:out value="${registroHoras.trabajador.name}"/>
                </td>
                <td>
                	<spring:url value="/reclamaciones/delete/{reclamacionId}" var="registroHorasUrl">
                		<spring:param name="registroHorasId" value="${registroHoras.id}"/>
                	</spring:url>
                	<a href="${fn:escapeXml(registroHorasUrl)}">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>