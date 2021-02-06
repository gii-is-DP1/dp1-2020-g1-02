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
<spring:url value="/resources/images/delete.jpg" var="delete"/>
<a href="/administradores/morosos">
		<button type="button" class="btn btn-default btn-lg">
  			<span class="glyphicon glyphicon-plus" aria-hidden="true"></span> Ver Morosos
		</button>
</a>
 <c:if test="${avisoServicio.size() >0 }">
    <h2>Contratos que expiran el  próximo mes</h2>
     <table id="contratosServicios" class="" style="background-color: yellow;">
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
        <c:forEach items="${avisoServicio}" var="contrato">
            <tr>
                <td>
                    <c:out value="${contrato.fechainicial}"/>
                </td>
                <td>
                    <c:out value="${contrato.fechafinal}"/>
                </td>
                <td>
                    <c:out value="${contrato.fechapago}"/>
                </td>
                <td>
                    <c:out value="${contrato.cliente.dni}"/>
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
    </c:if>
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
                    <c:out value="${contrato.fechainicial}"/>
                </td>
                <td>
                    <c:out value="${contrato.fechafinal}"/>
                </td>
                <td>
                    <c:out value="${contrato.fechapago}"/>
                </td>
                <td>
                    <c:out value="${contrato.cliente.dni}"/>
                </td>
                <td>
                    <c:out value="${contrato.presupuesto.id}"/>
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
    <c:if test="${avisoTrabajador.size() >0 }">
     <h2>Contratos que expiran el  próximo mes</h2>
     <table id="contratosTrabajador" class="" style="background-color: yellow;">
        <thead>
        <tr>
        	<th style="width: 200px;">Nombre</th>
        	<th style="width: 200px;">Apellidos</th>
        	 <th style="width: 200px;">DNI</th>
            <th style="width: 200px;">Fecha Inicial</th>
            <th style="width: 200px;">Fecha Final</th>
            <th style="width: 200px;">Sueldo</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${avisoTrabajador}" var="contrato">
            <tr>
           		<td>
                    <c:out value="${contrato.trabajador.nombre}"/> 
                </td>
                <td>
                	<c:out value="${contrato.trabajador.apellidos}"/> 
                </td>
                 <td>
                    <c:out value="${contrato.trabajador.dni}"/>
                </td>
                <td>
                    <c:out value="${contrato.fechainicial}"/>
                </td>
                <td>
                    <c:out value="${contrato.fechafinal}"/>
                </td>
                <td>
                    <c:out value="${contrato.sueldo}"/>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
   </c:if>
	<h2>Contratos de Trabajadores</h2>
    <table id="contratosServicios" class="table table-striped">
        <thead>
        <tr>
        	<th style="width: 200px;">Nombre</th>
        	<th style="width: 200px;">Apellidos</th>
        	 <th style="width: 200px;">DNI</th>
            <th style="width: 200px;">Fecha Inicial</th>
            <th style="width: 200px;">Fecha Final</th>
            <th style="width: 200px;">Sueldo</th>
            <th style="width: 200px;"></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${contratosTrabajador}" var="contrato">
            <tr>
           		<td>
                    <c:out value="${contrato.trabajador.nombre}"/> 
                </td>
                <td>
                	<c:out value="${contrato.trabajador.apellidos}"/> 
                </td>
                 <td>
                    <c:out value="${contrato.trabajador.dni}"/>
                </td>
                <td>
                    <c:out value="${contrato.fechainicial}"/>
                </td>
                <td>
                    <c:out value="${contrato.fechafinal}"/>
                </td>
                <td>
                    <c:out value="${contrato.sueldo}"/>
                </td>
                 <td>
                	<spring:url value="/contratosTrabajadores/delete/{contratoTrabajadorId}" var="contratotrabajadorUrl">
                		<spring:param name="contratoTrabajadorId" value="${contrato.id}"/>
                	</spring:url>
                	<a href="${fn:escapeXml(contratotrabajadorUrl)}"><img src="${delete}" width="30px"/></a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>