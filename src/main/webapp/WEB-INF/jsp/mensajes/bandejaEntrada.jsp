<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<petclinic:layout pageName="bandejaEntrada">
<spring:url value="/resources/images/leido.png" var="leido"/>
<spring:url value="/resources/images/cerrado.png" var="cerrado"/>
    <h2>Mensajes</h2>

	<spring:url value="/mensajes/new" var="mensajesUrl">
    </spring:url>
    <a href="${fn:escapeXml(mensajesUrl)}" class="btn btn-default">Nuevo mensaje</a>

    <table id="mensajesTable" class="table table-striped">
        <thead>
        <tr>
        	<th style="width: 5%;"></th>
            <th style="width: 15%;">Fecha</th>
            <th style="width: 15%;">Enviado por</th>
            <th style="width: 15%;">Asunto</th>
            <th style="width: 40%;">Cuerpo del mensaje</th>
            <th style="width: 10%;"></th>
            <th style="width: 15%;"></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${mensajes}" var="msj">
            <tr>
      
            	<td>
            	<c:if test="${msj.leido}" >
            		<img src="${leido}" width="30px" />
            	</c:if>
            	<c:if test="${!msj.leido}" >
    				<img src="${cerrado}" width="30px"/>
            	</c:if>
            	</td>
                <td>
                    <c:out value="${msj.fecha}"/>
                </td>
                <td>
                    <c:out value="${msj.emisor.username}"/>
                </td>
                 <td>
                    <c:out value="${msj.asunto}"/>
                </td>
                <td>
                    <c:out value="${msj.cuerpo}"/>
                </td>
                
               <td>
               	<c:if test="${!msj.leido}" >
               <form:form modelAttribute="mensaje" class="form-horizontal" action="/mensajes/leido">
       						<input type="hidden" id="id" name="id" value=' <c:out value="${msj.id}"/>'>
       						<button type="submit">Marcar como leido</button>
       				</form:form>
       				</c:if>
               </td>
                <td>
                	<spring:url value="mensajes/new/{mId}" var="addUrl">
                        <spring:param name="mId" value="${mensaje.id}"/>
                        </spring:url>
                        <a href="${fn:escapeXml(addUrl)}" class="btn btn-default">Responder</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    
    
</petclinic:layout>