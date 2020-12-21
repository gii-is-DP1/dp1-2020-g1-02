<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="contratosTrabajadores">
    <h2>Contratos Trabajadores</h2>

    <table id="eventsTable" class="table table-striped">
        <thead>
        <tr>
        	<th style="width: 150px;">ID_CONTRATO</th>
            <th style="width: 150px;">FECHA INICIO</th>
            <th style="width: 150px;">FECHA FIN</th>
          <!--    <th style="width: 150px;">ID_TRABAJADOR</th>  -->
            <th style="width: 150px;">SUELDO</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${contratosTrabajadores}" var="contrato">
            <tr>
            	<td>
                    <c:out value="${contrato.id}"/>
                </td>
                <td>
                    <c:out value="${contrato.fechainicial}"/>
                </td>
                <td>
                    <c:out value="${contrato.fechafinal}"/>
                </td>
<%--                  <td>
                    <c:out value="${contrato.trabajadooid}"/>
                </td>  --%>
                <td>
                    <c:out value="${contrato.sueldo}"/>
                </td>
                 <td>
                	<spring:url value="/contratosTrabajadores/delete/{contratoTrabajadorId}" var="contratotrabajadorUrl">
                		<spring:param name="contratoTrabajadorId" value="${contrato.id}"/>
                	</spring:url>
                	<a href="${fn:escapeXml(contratotrabajadorUrl)}">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>
