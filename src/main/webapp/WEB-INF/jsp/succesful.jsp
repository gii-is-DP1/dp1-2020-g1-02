<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="succesful">

    <spring:url value="/resources/images/exito.jpg" var="exito"/>
    <div class="row">
    	<div class="col-6">
    		<img src="${exito}" class=""/>
    	</div>
    	<div class="col-6 text-center">
    		<h2 style="color: green;">Todo ha funcionado correctamente.</h2>
    		<!--  <p>${exception.message}</p-->
    		<h1><c:out value="${message}"/></h1>
    	</div>
    </div>
</petclinic:layout>
