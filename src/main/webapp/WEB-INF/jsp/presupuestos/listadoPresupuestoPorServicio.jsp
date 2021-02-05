<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="user" value="${pageContext.request.userPrincipal.name}" />
<c:set var="espera" value="Espera" />

<petclinic:layout pageName="presupuestos">
    <h2>PRESUPUESTOS DEL SERVICIO: <c:out value="${presupuestos[0].servicio.id}"/> </h2>
	
	
    <table id="eventsTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;">PRECIO</th>
            <th style="width: 150px;">ESTADO</th>
            <th style="width: 150px;"></th>
            <th style="width: 150px;"></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${presupuestos}" var="presupuesto">
            <tr>
                <td>
                    <c:out value="${presupuesto.precio}"/>
                </td>
                <td>
                    <c:out value="${presupuesto.estado}"/>
                </td>
              
               <c:if test="${presupuesto.estado eq 'Espera'}">
               		<sec:authorize access="hasAuthority('cliente')">
               			 <td>
               	 				<form:form modelAttribute="presupuesto" class="form-horizontal" action="aceptar">
       								<input type="hidden" id="id" name="id" value=' <c:out value="${presupuesto.id}"/>'>
       								<button type="submit">Aceptar presupuesto</button>
       							</form:form>
       					 </td>
         		
         				 <td>
               	 				<form:form modelAttribute="presupuesto" class="form-horizontal" action="rechazar">
       								<input type="hidden" id="id" name="id" value=' <c:out value="${presupuesto.id}"/>'>
       								<button type="submit">Rechazar presupuesto</button>
       							</form:form>
       				 	 </td>
       				</sec:authorize>
       				<sec:authorize access="hasAuthority('administrador')"><td></td> <td></td></sec:authorize>
 			  </c:if>
 			  <c:if test="${presupuesto.estado != 'Espera'}"><td></td> <td></td></c:if>
 			  
 			  
      	 	  <c:if test="${presupuesto.estado eq 'Aceptado'}"> <c:if test="${presupuesto.contrato eq null}">
      	 	  	
      	 	  	<sec:authorize access="hasAuthority('administrador')">
      	 	  	
      	 	    <td>
                	<spring:url value="/contratosServicios/{pId}/new" var="contratoUrl">
                		<spring:param name="pId" value="${presupuesto.id}"/>
                	</spring:url>
                	<a href="${fn:escapeXml(contratoUrl)}">Crear Contrato</a>
                </td>
                </sec:authorize>
                <sec:authorize access="hasAuthority('cliente')"><td></td> <td></td></sec:authorize>
      	 	  </c:if></c:if>
       		   <c:if test="${presupuesto.estado != 'Aceptado'}"><td></td> <td></td></c:if>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>

