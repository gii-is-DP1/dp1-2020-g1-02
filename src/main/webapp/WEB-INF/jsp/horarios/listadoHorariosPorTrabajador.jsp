<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>



<petclinic:layout pageName="horarios">
    <h2>HORARIOS</h2>
	
	<sec:authorize access="hasAuthority('administrador')">
	<h2>Del Trabajador <c:out value="${trabajador}"/></h2>
	<spring:url value="/horarios/new/{trabajadorId}" var="newUrl">
         <spring:param name="trabajadorId" value="${horarios[0].trabajador.id}" />
    </spring:url>
     <a href="${fn:escapeXml(newUrl)}">Nuevo Horario</a>
	</sec:authorize>
	
    <table id="horariosTable" class="table table-striped">
        <thead>
        <tr>
       		 <th style="width: 300px;">FECHA</th>
            <th style="width: 150px;">HORA_INICIO</th>
            <th style="width: 150px;">HORA_FIN</th>
            <th style="width: 150px;">NOMBRE_TRABAJADOR</th>
            <th style="width: 150px;">DESCRIPCION</th>
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
	              <c:out value="${horario.hora_inicio}" />            
                </td>
                 <td>
					<c:out value="${horario.hora_fin}" />        
                </td>
                <td>
                    <c:out value="${horario.trabajador.nombre}"/>
                </td>
                <td>
                    <c:out value="${horario.descripcion}"/>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>