<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="error">

    <spring:url value="/resources/images/error.png" var="petsImage"/>
    <div class="row">
    	<div class="col-6">
    		<img  src="${petsImage}" class=""/>
    	</div>
    	<div class="col-6 text-center">
    		<h2 style="color: red;">Ha surgido un problema.</h2>
    		<p>${exception.message}</p>
    		<p>${hola}</p>
    		 <h1><c:out value="${error}"/></h1>
    	</div>
    </div>
</petclinic:layout>
