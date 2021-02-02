<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="bandejaEntrada">
    <h2>Ofertas</h2>

    <table id="mensajesTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 15%;">Fecha</th>
            <th style="width: 15%;">Enviado por</th>
            <th style="width: 15%;">Asunto</th>
            <th style="width: 55%;">Cuerpo del mensaje</th>
            <th style="width: 15%;"></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${mensajes}" var="msj">
            <tr>
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