<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<c:set var="user" value="${pageContext.request.userPrincipal.name}" />
<c:set var="admin" value="admin" />
<petclinic:layout pageName="valoraciones">
    <h2>Valoraciones</h2>
    
    <c:if test = "${user == admin}">
    <h3> Filtrar por nombre del cliente: </h3>
        <form action="/valoraciones/filtrado"> 
		   	<input type="text" name="nombreCli" value="${filtrado}"> 
		   	<button type="submit"> Filtrar </button>
    	</form>
     </c:if>

    <table id="valoracionesTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;">Fecha</th>
            <th style="width: 200px;">Nivel de Satisfaccion</th>
            <th>ID Cliente</th>
            <th>ID Servicio</th> 
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${valoraciones}" var="valoracion">
            <tr>
                <td>
                    <c:out value="${valoracion.fecha}"/>
                </td>
                <td>
                    <c:out value="${valoracion.nivelsatisfaccion}"/>
                </td>
                <td>
                    <c:out value="${valoracion.cliente.id}"/>
                </td>
                <td>
                    <c:out value="${valoracion.servicio.id}"/>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>