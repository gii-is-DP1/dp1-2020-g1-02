<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="horarios">
    <h2>HORARIO</h2>
	
	
    <table id="horariosTable" class="table table-striped">
        <thead>
        <tr>
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
                    <fmt:parseDate value="${horario.hora_inicio}" pattern="yyyy-MM-dd'T'HH:mm" var="date"/>
					<fmt:formatDate value="${date}" type = "both" dateStyle="long" timeStyle="short" />
                </td>
                <td>
                    <fmt:parseDate value="${horario.hora_fin}" pattern="yyyy-MM-dd'T'HH:mm" var="date"/>
					<fmt:formatDate value="${date}" type = "both" dateStyle="long" timeStyle="short" />
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