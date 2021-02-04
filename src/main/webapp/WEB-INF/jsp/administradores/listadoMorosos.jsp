<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="contratosServicios">
    <h2>Lista de Morosos</h2>

    <table id="listaMorosos" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;">Nombre</th>
            <th style="width: 200px;">Apellidos</th>
            <th>Telefono</th>
            <th>Direccion</th>
            <th>Dni</th>
            <th>Correo</th>
            <%-- <th>Fecha final de contrato</th>--%>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${morosos}" var="moroso">
            <tr>
                <td>
                    <c:out value="${moroso.nombre}"/>
                </td>
                <td>
                    <c:out value="${moroso.apellidos}"/>
                </td>
                <td>
                    <c:out value="${moroso.telefono}"/>
                </td>
                <td>
                    <c:out value="${moroso.direccion}"/>
                </td>
                <td>
                    <c:out value="${moroso.dni}"/>
                </td>
                 <td>
                    <c:out value="${moroso.correo}"/>
                </td>
                <%-- <td>
                    <c:out value="${moroso.servicios.contrato.fechafinal}"/>
                </td>--%>
                
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>