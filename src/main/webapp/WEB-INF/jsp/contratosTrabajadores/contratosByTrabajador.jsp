<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<spring:url value="/resources/images/delete.jpg" var="delete"/>

<petclinic:layout pageName="contratosTrabajadores">
    <h2>Contratos del trabajador: <c:out value="${contratos[0].trabajador.nombre}"/>&nbsp<c:out value="${contratos[0].trabajador.apellidos}"/></h2>
         <spring:url value="/contratosTrabajadores/{tId}/new" var="trabajadorUrl">
         <spring:param name="tId" value="${contratos[0].trabajador.id}"/>
         </spring:url>
         <a href="${fn:escapeXml(trabajadorUrl)}">Nuevo Contrato</a>
    <table id="eventsTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 200px;">FECHA INICIO</th>
            <th style="width: 200px;">FECHA FIN</th>
            <th style="width: 200px;">SUELDO</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${contratos}" var="contrato">
            <tr>
                 <td>
	                <fmt:parseDate value="${contrato.fechainicial}" pattern="yyyy-MM-dd" var="date"/>
	                <fmt:formatDate value="${date}" type = "date" dateStyle="long"/>     
                </td>
                <td>
                     <fmt:parseDate value="${contrato.fechafinal}" pattern="yyyy-MM-dd" var="date"/>
	                 <fmt:formatDate value="${date}" type = "date" dateStyle="long"/> 
                </td>
                <td>
                    <c:out value="${contrato.sueldo}"/>
                </td>
                 <td>
                	<spring:url value="/contratosTrabajadores/delete/{contratoTrabajadorId}" var="contratotrabajadorUrl">
                		<spring:param name="contratoTrabajadorId" value="${contrato.id}"/>
                	</spring:url>
                	<a href="${fn:escapeXml(contratotrabajadorUrl)}"><img src="${delete}" width="30px"/></a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>
