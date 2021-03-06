<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>


<petclinic:layout pageName="Servicios">
 <jsp:attribute name="customScript">
    </jsp:attribute>
    <jsp:body>
       <h2>Asignar trabajadores</h2>
       <form:form modelAttribute="servicio" class="form-horizontal" action="/servicios/asignar/save">
       		<div class="form-group has-feedback">
       		
       			<input type="hidden" name="id" value=' <c:out value="${servicio.id}"/>'>
       			
       			
       			 <label for="trabajadores">  Trabajadores a asignar:</Label>  </br>
			       <select class="form-control" name="trabajadores" size="${trabajadores.size()}" multiple>
          			 <c:forEach items="${trabajadores}" var="trabajador">
			        	<option value="${trabajador.id}"><c:out value="${trabajador.nombre} ${trabajador.apellidos}"/></option>                  
			        </c:forEach>
					</select>
					
				

  				
       		</div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button class="btn btn-default" type="submit">Guardar Servicio</button>
                </div>
            </div>
        </form:form>
    </jsp:body>

</petclinic:layout>
