<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="events">
    <h2>Events</h2>

    <table id="eventsTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;">Name</th>
            <th style="width: 200px;">apellidos</th>
            <th>dni</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${events}" var="event">
            <tr>
                <td>
                    <c:out value="${event.name}"/>
                </td>
                <td>
                    <c:out value="${event.apellidos}"/>
                </td>
                 <td>
                    <c:out value="${event.dni}"/>
                </td>
                <td>
                	<spring:url value="/events/delete/{eventId}" var="eventUrl">
                		<spring:param name="eventId" value="${event.id}"/>
                	</spring:url>
                	<a href="${fn:escapeXml(eventUrl)}">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>
