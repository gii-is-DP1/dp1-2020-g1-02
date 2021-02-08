<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="curriculums">
	<jsp:body>
       
		    <h2>Curriculums</h2>
		    <table id="curriculumsTable" class="table table-striped">
		        <thead>
		        <tr>
		            <th style="width: 15%;">Nombre</th>
		            <th style="width: 15%;">Apellidos</th>
		            <th style="width: 15%;">Telefono</th>
		            <th style="width: 15%;">Correo</th>
		            <th style="width: 15%;">Descripcion</th>
		            <th style="width: 10%;">TipoCategoria</th>
		            <th style="width: 10%"></th>
		            <th style="width: 5%;"></th>
		        </tr>
		        </thead>
		        <tbody>
		        <c:forEach items="${curriculum}" var="curriculum">
		            <tr>
		                <td>
		                    <c:out value="${curriculum.nombre}"/>
		                </td>
		                <td>
		                    <c:out value="${curriculum.apellidos}"/>
		                </td>
		                <td>
		                    <c:out value="${curriculum.telefono}"/>
		                </td>
		                <td>
		                    <c:out value="${curriculum.correo}"/>
		                </td>
		                <td>
		                    <c:out value="${curriculum.descripcion}"/>
		                </td>
		                <td>
		                    <c:out value="${curriculum.tipocategoria}"/>
		                </td>
						<td>
               		 	<c:if test="${curriculum.trabajador eq null}">
							<spring:url value="/users/newTrabajador/{idCurriculum}" var="trabajadorUrl">
							<spring:param name="idCurriculum" value="${curriculum.id}"/>
                			</spring:url>
                			<a href="${fn:escapeXml(trabajadorUrl)}">Añadir Trabajador</a>               			
               		 	</c:if>		                
               		 	</td>
               		 	<td>
               		 	<c:if test="${curriculum.trabajador eq null}">
                			<spring:url value="/curriculums/delete/{curriculumId}" var="curriculumUrl">
                				<spring:param name="curriculumId" value="${curriculum.id}"/>
                			</spring:url>
                			<a href="${fn:escapeXml(curriculumUrl)}"><img src="/resources/images/delete.jpg" width="30px"/></a>
                			</c:if>		
               		 	</td>
		            </tr>
		        </c:forEach>
		        </tbody>
		    </table>

    </jsp:body>
</petclinic:layout>