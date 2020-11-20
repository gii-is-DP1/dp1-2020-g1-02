<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="proveedores">
    <h2>Proveedores</h2>

    <table id="proveedoresTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;">Nombre Empresa</th>
            <th style="width: 150px;">Telefono</th>
            <th style="width: 150px;">Email</th>
            <th style="width: 150px;">Direccion</th>


        </tr>
        </thead>
        <tbody>
        <c:forEach items="${prov}" var="proveedor">
            <tr>
                <td>
                    <c:out value="${proveedor.name}"/>
                </td>
                <td>
                    <c:out value="${proveedor.telefono}"/>
                </td>
                 <td>
                    <c:out value="${proveedor.email}"/>
                </td>
                <td>
                    <c:out value="${proveedor.direccion}"/>
                </td>
                
                <td>
                	<spring:url value="/proveedores/delete/{provName}" var="provUrl">
                		<spring:param name="provName" value="${proveedor.name}"/>
                	</spring:url>
                	<a href="${fn:escapeXml(provUrl)}">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>