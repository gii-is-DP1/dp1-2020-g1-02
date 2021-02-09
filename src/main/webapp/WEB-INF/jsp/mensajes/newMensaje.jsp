<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="ofertas">
    <h2> Nuevo mensaje
    </h2>
    <form:form modelAttribute="mensaje" class="form-horizontal" id="add-mensajes-form" action="/mensajes/save">
        <div class="form-group has-feedback">
        
        	
        	<petclinic:inputField label="De:" name="emisor" disabled="true"/>
 			 
 			
 			
 			<c:choose>
			    <c:when test="${mensaje.receptores != null}">
			      
			      <petclinic:inputField label="Para:" name="receptores" disabled="true"/>
			        
			    </c:when>    
			    <c:otherwise>
			    <Label for="receptores">  Para:</Label>  </br>
			       <select class="form-control" name="receptores" size="${users.size()} }" multiple>
          			 <c:forEach items="${users}" var="user">
			           
			                   <option><c:out value="${user}"/></option>
			                
			        </c:forEach>
					</select>
			    </c:otherwise>
			</c:choose>
           
           
		   <petclinic:inputField label="Fecha" name="fecha" disabled="true"/>
           <petclinic:inputField label="Asunto" name="asunto"/>
           <petclinic:inputField label="Mensaje" name="cuerpo"/> 
           <input type="hidden" name="leido" id="leido" value="false">
           
            
        </div>
       	<div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button class="btn btn-default" type="submit">Enviar mensaje </button>
                </div>
        </div>
    </form:form>
</petclinic:layout>