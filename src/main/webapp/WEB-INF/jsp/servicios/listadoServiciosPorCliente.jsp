<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="serviciosV" value="${serviciosV}" />
<petclinic:layout pageName="servicios">
    <h2>Mis Servicios</h2>
    
	<spring:url value="/servicios/new" var="serviciosUrl">
    </spring:url>
    <a href="${fn:escapeXml(serviciosUrl)}" class="btn btn-default">Solicitar nuevo servicio</a>
	
	
    <table id="eventsTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;">LUGAR</th>
            <th style="width: 150px;">FECHAINICIO</th>
            <th style="width: 150px;">FECHAFIN</th>
            <th style="width: 150px;">TIPOCATEGORIA</th>
            <th style="width: 150px;">ESTADO</th>
          	
            <th style="width: 150px;"></th>

            <th style="width: 150px;"></th>
            <th style="width: 150px;"></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${servicios}" var="servicio">
            <tr>
                <td>
                    <c:out value="${servicio.lugar}"/>
                </td>
                <td>
                    <c:out value="${servicio.fechainicio}"/>
                </td>
                 <td>
                    <c:out value="${servicio.fechafin}"/>
                </td>
                <td>
                    <c:out value="${servicio.tipocategoria}"/>
                </td>
                <td>
                    <c:out value="${servicio.estado}"/>
                </td>
                <td>
                	<spring:url value="/reclamaciones/new/{oId}" var="reclamacionUrl">
              		<spring:param name="oId" value="${servicio.id}"/>
	              	</spring:url>
	              	<a href="${fn:escapeXml(reclamacionUrl)}">Poner reclamacion</a>
                </td>
                
            
				    
				      <td>
				      <c:if test="${not fn:contains(serviciosV, servicio.id)}">
	                	<spring:url value="/valoraciones/new/{oId}" var="reclamacionUrl">
	              		<spring:param name="oId" value="${servicio.id}"/>
		              	</spring:url>
		              	<a href="${fn:escapeXml(reclamacionUrl)}">Valorar</a>
		              	 </c:if>
	                </td>
				        
				     
				    
				
              
                <td>
                	<spring:url value="/servicios/{servicioId}/presupuestos" var="servicioUrl">
                		<spring:param name="servicioId" value="${servicio.id}"/>
                	</spring:url>
                	<a href="${fn:escapeXml(servicioUrl)}">Ver Presupuestos</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>

