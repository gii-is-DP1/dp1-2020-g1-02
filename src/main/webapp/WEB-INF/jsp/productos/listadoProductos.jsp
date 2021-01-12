<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="productos">
    <h2>Productos</h2>

    <table id="productosTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;">Nombre</th>
            <th style="width: 150px;">Cantidad en Stock</th>
            <th style="width: 150px;"></th>
            <!-- <th style="width: 150px;"> Solicitar m�s </th> -->


        </tr>
        </thead>
        <tbody>
        <c:forEach items="${producto}" var="producto">
            <tr>
                <td>
                    <c:out value="${producto.name}"/>
                </td>
                <td>
                    <c:out value="${producto.cantidad}"/>
                </td>
                
                <td>
                	<spring:url value="/productos/{productId}/restar" var="productUrl">
                		<spring:param name="productId" value="${producto.id}"/>
                	</spring:url>
                	<a href="${fn:escapeXml(productUrl)}">Restar</a>
                </td>
                <%-- <td>
                	<spring:url value="/productos/{productId}/solicitar" var="productUrl">
                		<spring:param name="productId" value="${producto.id}"/>
                	</spring:url>
                	<a href="${fn:escapeXml(productUrl)}">Solicitar</a>
                </td> --%>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>