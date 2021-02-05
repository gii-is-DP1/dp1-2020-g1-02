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
<petclinic:layout pageName="instalaciones">
    <h2>Instalaciones</h2>
    
      <c:if test = "${user == admin}">
    <h3> Filtrar por nombre del cliente: </h3>
        <form action="/instalaciones/filtrado"> 
		   	<input type="text" name="nombreCli" value="${filtrado}"> 
		   	<button type="submit"> Filtrar </button>
    	</form>
     </c:if>
    
    <a href="/instalaciones/new">
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
        <c:forEach items="${instalaciones}" var="instalacion">
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
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>