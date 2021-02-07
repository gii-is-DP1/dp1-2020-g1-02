<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<spring:url value="/resources/images/star.jpg" var="star"/>

<petclinic:layout pageName="valoraciones">
    <h2>Valoraciones</h2>
    

 <c:forEach items="${valoraciones}" var="valoracion">
    <div>
         <div>
            <th style="width: 150px;"><c:out value="${valoracion.key}"/></th>
            
        </div>
         <div>
         	<c:forEach begin="1" step="1" end="${valoracion.value}" >
						<img src="${star}" width="30px"/>
					</c:forEach>
               </div>
 
        
    </div>
    </c:forEach>
</petclinic:layout>