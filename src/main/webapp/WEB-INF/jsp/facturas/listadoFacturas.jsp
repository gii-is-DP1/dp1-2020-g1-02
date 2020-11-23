<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="facturas">
    <h2>Facturas</h2>

    <table id="facturasTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;">Fecha</th>
            <th style="width: 150px;">Precio total</th>


        </tr>
        </thead>
        <tbody>
        <c:forEach items="${factura}" var="factura">
            <tr>
                <td>
                    <c:out value="${factura.fecha}"/>
                </td>
                <td>
                    <c:out value="${factura.precio_total}"/>
                </td>
                
                <td>
                	<spring:url value="/facturas/delete/{facturaId}" var="facturaUrl">
                		<spring:param name="facturaId" value="${factura.id}"/>
                	</spring:url>
                	<a href="${fn:escapeXml(facturaUrl)}">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>