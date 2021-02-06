
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<spring:url value="/resources/images/delete.jpg" var="delete"/>

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
        	<th style="width: 300px;">FECHA</th>
            <th style="width: 300px;">HORA_INICIO</th>
            <th style="width: 300px;">HORA_FIN</th>
            <th style="width: 150px;">NOMBRE_TRABAJADOR</th>
            <th style="width: 150px;">DESCRIPCION</th>
             <th style="width: 150px;"></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${horarios}" var="horario">
            <tr>
                <td>
                    <fmt:parseDate value="${horario.fecha}" pattern="yyyy-MM-dd" var="date"/>
					<fmt:formatDate value="${date}" type = "date" dateStyle="long"/>
                </td>
                <td>
	                <fmt:parseDate value="${horario.hora_inicio}" pattern="HH:mm:ss" var="inicio"/>
					<fmt:formatDate value="${inicio}" type = "time" timeStyle="short"/>  
					         
                </td>
                 <td>
	              <fmt:parseDate value="${horario.hora_fin}" pattern="HH:mm:ss" var="fin"/>
					<fmt:formatDate value="${fin}" type = "time" timeStyle="short"/>             
                </td>
                <td>
                    <c:out value="${horario.trabajador.nombre}"/>
                </td>
                <td>
                    <c:out value="${horario.descripcion}"/>
                </td>
                <td>
                	<spring:url value="/horarios/delete/{horarioId}" var="horarioUrl">
                		<spring:param name="horarioId" value="${horario.id}"/>
                	</spring:url>
                	<a href="${fn:escapeXml(horarioUrl)}"><img src="${delete}" width="30px"/></a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>