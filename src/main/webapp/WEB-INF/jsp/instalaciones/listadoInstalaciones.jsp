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
    
    
    <spring:url value="/instalaciones/new" var="instalacionesUrl">
    </spring:url>
    <a href="${fn:escapeXml(instalacionesUrl)}" class="btn btn-default">Nueva instalacion</a>
  

    <table id="instalacionesTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 30%;">Lugar</th>
            <th style="width: 30%;">Dimension</th>
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
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>