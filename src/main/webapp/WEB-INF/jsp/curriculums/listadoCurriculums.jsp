<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="curriculums">
    <h2>Curriculums</h2>

    <table id="curriculumsTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;">Nombre</th>
            <th style="width: 200px;">TipoCategoria</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${curriculum}" var="curriculum">
            <tr>
                <td>
                    <c:out value="${curriculum.name}"/>
                </td>
                <td>
                    <c:out value="${curriculum.tipocategoria}"/>
                </td>
                <td>
                	<spring:url value="/curriculums/delete/{curriculumId}" var="curriculumUrl">
                		<spring:param name="curriculumId" value="${curriculum.id}"/>
                	</spring:url>
                	<a href="${fn:escapeXml(curriculumUrl)}">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>