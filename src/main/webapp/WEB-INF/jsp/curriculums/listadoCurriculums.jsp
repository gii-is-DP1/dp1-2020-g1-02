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
       <div class="container">
		    <h2>Curriculums</h2>
		    <table id="curriculumsTable" class="table table-striped">
		        <thead>
		        <tr>
		            <th style="width: 150px;">Nombre</th>
		            <th style="width: 150px;">Apellidos</th>
		            <th style="width: 150px;">Telefono</th>
		            <th style="width: 150px;">Correo</th>
		            <th style="width: 150px;">Descripcion</th>
		            <th style="width: 200px;">TipoCategoria</th>
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
                			<spring:url value="/delete/{curriculumId}" var="curriculumUrl">
                				<spring:param name="curriculumId" value="${curriculum.id}"/>
                			</spring:url>
                			<a href="${fn:escapeXml(curriculumUrl)}">Eliminar</a>
               		 	</td>
               		 	<td>
               		 	<c:if test="${curriculum.trabajador eq null}">
							<spring:url value="/users/newTrabajador/{idCurriculum}" var="trabajadorUrl">
							<spring:param name="idCurriculum" value="${curriculum.id}"/>
                			</spring:url>
                			<a href="${fn:escapeXml(trabajadorUrl)}">Añadir Trabajador</a>               			
               		 	</c:if>		                
               		 	</td>
		            </tr>
		        </c:forEach>
		        </tbody>
		    </table>
	     </div>
    </jsp:body>
</petclinic:layout>