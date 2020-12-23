<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<petclinic:layout pageName="servicios">
    <h2>Servicios</h2>
	
	
    <table id="eventsTable" class="table table-striped">
        <thead>
        <tr>
        	<th style="width: 150px;">ID_SERVICIO</th>
            <th style="width: 150px;">LUGAR</th>
            <th style="width: 150px;">FECHAINICIO</th>
            <th style="width: 150px;">FECHAFIN</th>
            <th style="width: 150px;">TIPOCATEGORIA</th>
            <th style="width: 150px;">ESTADO</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${servicios}" var="servicio">
            <tr>
            	<td>
                    <c:out value="${servicio.id}"/>
                </td>
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
                	<spring:url value="/servicios/delete/{servicioId}" var="servicioUrl">
                		<spring:param name="servicioId" value="${servicio.id}"/>
                	</spring:url>
                	<a href="${fn:escapeXml(servicioUrl)}">Delete</a>
                </td>
               
               <!--    ACEPTAR RECHAZAR SERVICIO   -->
               
                <td>
               	 	<form:form modelAttribute="servicio" class="form-horizontal" action="/servicios/aceptar">
       						<input type="hidden" id="id" name="id" value=' <c:out value="${servicio.id}"/>'>
       						<button type="submit">Aceptar solicitud</button>
       				</form:form>
       			 </td>
         
                <td>
               	 	<form:form modelAttribute="servicio" class="form-horizontal" action="/servicios/save">
       						<input type="hidden" id="id" name="id" value=' <c:out value="${servicio.id}"/>'>
       						<input type="hidden" name="lugar" value=' <c:out value="${servicio.lugar}"/>'>
       						<input type="hidden" name="fechainicio" value='<petclinic:localDate date="${servicio.fechainicio}" pattern="yyyy/MM/dd"/>'>
       						<input type="hidden" name="fechafin" value='<petclinic:localDate date="${servicio.fechafin}" pattern="yyyy/MM/dd"/>'>
       						<input type="hidden" name="tipocategoria" value=' <c:out value="${servicio.tipocategoria}"/>'>
     						<input type="hidden" name="estado" value="Rechazado"/>
       						<button type="submit">Rechazar solicitud</button>
        			</form:form>
               </td> 
                <!-- AÑADIR BOTON PARA CREAR PRESUPUESTO  -->
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>

