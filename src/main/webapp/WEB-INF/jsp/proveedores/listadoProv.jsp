<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<spring:url value="/resources/images/delete.png" var="delete"/>
<petclinic:layout pageName="proveedores">
    
    <h2>Proveedores</h2>

    <table id="proveedoresTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 23%;">Nombre Empresa</th>
            <th style="width: 23%;">Telefono</th>
            <th style="width: 23%;">Email</th>
            <th style="width: 23%;">Direccion</th>
            <th style="width: 8%;"> </th>


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
                	<spring:url value="/proveedores/delete/{provId}" var="provUrl">
                		<spring:param name="provId" value="${proveedor.id}"/>
                	</spring:url> 
                	<a href="${fn:escapeXml(provUrl)}"><img src="${delete}" width="30px"/></a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    
    
</petclinic:layout>