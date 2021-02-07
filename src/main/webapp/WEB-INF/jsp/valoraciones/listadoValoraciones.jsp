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
    
         <div class="container">
            <div class="white_bg">
            <div class="row">
			    <c:forEach items="${valoraciones}" var="valoracion" varStatus="index">
							    <div class="col-xl-3 col-lg-3 col-md-6 col-sm-12">
			                  		<div class="for_box">
							            <i><img src="resources/icon/${index.index}.png"/></i>
					                    <h3><c:out value="${valoracion.key}"/></h3>
					                    <h4> Valoracion de nuestros clientes</h4>
							            <p> <c:forEach begin="1" step="1" end="${valoracion.value}" >
													<img src="${star}" width="30px"/>
												</c:forEach>
										</p>
							        </div>
							        
			   					</div>
			    		</c:forEach>
    		
    		</div>
         </div>
       </div>
       </br>
</petclinic:layout>