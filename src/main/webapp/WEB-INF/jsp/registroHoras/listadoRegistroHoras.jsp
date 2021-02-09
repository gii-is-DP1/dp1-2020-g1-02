<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<spring:url value="/resources/images/delete.png" var="delete"/>
<petclinic:layout pageName="registro_horas">
    <h2>Registro de Horas del trabajador: <c:out value="${registrohoras[0].trabajador.nombre} ${registrohoras[0].trabajador.apellidos}"/></h2>
    

    <table id="registro_horastable" class="table table-striped">
        <thead>
        <tr>
        	<th style="width: 180px;">Fecha</th>
            <th style="width: 180px;">Hora Inicio</th>
            <th style="width: 180px;">Hora Fin</th>
            <th> </th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${registrohoras}" var="registrohora">
            <tr>
             <td>
                    <fmt:parseDate value="${registrohora.fecha}" pattern="yyyy-MM-dd" var="date"/>
					<fmt:formatDate value="${date}" type = "date" dateStyle="long"/>
                </td>
                <td>
	              <c:out value="${registrohora.hora_inicio}" />            
                </td>
                 <td>
					<c:out value="${registrohora.hora_fin}" />
                </td>      
                <td>
                	<spring:url value="/registroHoras/delete/{registroHorasId}" var="registroHorasUrl">
                		<spring:param name="registroHorasId" value="${registrohora.id}"/>
                	</spring:url>
                	<a href="${fn:escapeXml(registroHorasUrl)}"><img src="${delete}" width="30px"/></a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
        
        <h4>Horas totales:  <c:out value="${total}"/>   </h4>
    </table>
</petclinic:layout>