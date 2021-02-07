<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<petclinic:layout pageName="contratosServicios"> 
	
<%--     <h2>Contratos que expiran el  próximo mes</h2>
     <table id="contratosServicios" class="" style="background-color: yellow;">
        <thead>
        <tr>
            <th style="width: 150px;">ID</th>
            <th style="width: 200px;">Fecha Inicial</th>
            <th style="width: 200px;">Fecha Final</th>
            <th style="width: 200px;">Fecha Pago</th>
            <th style="width: 200px;">DNI del cliente</th>
            <th style="width: 200px;">ID Presupuesto</th>
            <th style="width: 200px;">Periodo de prueba</th>
            <th style="width: 200px;">Servicio</th>
         
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${aviso}" var="contrato">
            <tr>
                <td>
                   <fmt:parseDate value="${contrato.fechainicial}" pattern="yyyy-MM-dd" var="date"/>
	                <fmt:formatDate value="${date}" type = "date" dateStyle="long"/>     
                </td>
                <td>
                    <fmt:parseDate value="${contrato.fechafinal}" pattern="yyyy-MM-dd" var="date"/>
	                 <fmt:formatDate value="${date}" type = "date" dateStyle="long"/> 
                </td>
                <td>
                    <fmt:parseDate value="${contrato.fechapago}" pattern="yyyy-MM-dd" var="date"/>
	                 <fmt:formatDate value="${date}" type = "date" dateStyle="long"/> 
                </td>
                <td>
                    <c:out value="${contrato.presupuesto.servicio.cliente.dni}"/>
                </td>
                <td>
                    <c:out value="${contrato.presupuesto.precio}"/>
                </td>
                <td>
                    <c:out value="${contrato.periodoPrueba}"/>
                </td>
                <td>
                    <c:out value="${contrato.presupuesto.servicio.tipocategoria}"/>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table> --%>

	<h2>Contratos de Servicios</h2>
    <table id="contratosServicios" class="table table-striped">
        <thead>
        <tr>

            <th style="width: 200px;">Fecha Inicial</th>
            <th style="width: 200px;">Fecha Final</th>
            <th style="width: 200px;">Fecha Pago</th>
            <th style="width: 200px;">DNI del cliente</th>
            <th style="width: 200px;">ID Presupuesto</th>
            <th style="width: 200px;">Periodo de prueba</th>
            <th style="width: 200px;">Servicio</th>
         
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${contratosServicios}" var="contrato">
             <tr>
                <td>
                   <fmt:parseDate value="${contrato.fechainicial}" pattern="yyyy-MM-dd" var="date"/>
	                <fmt:formatDate value="${date}" type = "date" dateStyle="long"/>     
                </td>
                <td>
                    <fmt:parseDate value="${contrato.fechafinal}" pattern="yyyy-MM-dd" var="date"/>
	                 <fmt:formatDate value="${date}" type = "date" dateStyle="long"/> 
                </td>
                <td>
                    <fmt:parseDate value="${contrato.fechapago}" pattern="yyyy-MM-dd" var="date"/>
	                 <fmt:formatDate value="${date}" type = "date" dateStyle="long"/> 
                </td>
                <td>
                    <c:out value="${contrato.presupuesto.servicio.cliente.dni}"/>
                </td>
                <td>
                    <c:out value="${contrato.presupuesto.precio}"/>
                </td>
                <td>
                    <c:out value="${contrato.periodoPrueba}"/>
                </td>
                <td>
                    <c:out value="${contrato.presupuesto.servicio.tipocategoria}"/>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>