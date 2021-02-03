<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>


<petclinic:layout pageName="Presupuestos">
 <jsp:attribute name="customScript">
    </jsp:attribute>
    <jsp:body>
       <h2>Presupuesto</h2>
       <form:form modelAttribute="presupuesto" class="form-horizontal" action="/servicios/presupuestos/save"> 
       		<div class="form-group has-feedback">
       			
       			 <input type="hidden" name="servicio" value='<c:out value="${presupuesto.servicio.id}"/>'/>
       			<petclinic:inputField label="precio" name="precio"/>
       			<input type="hidden" name="estado" value="Espera"/>
       			 <label for="tipopresupuesto">Tipo de Presupuesto</label>
  				<select id="tipopresupuesto" name="tipopresupuesto">
    				<option value="PorHoras">Por horas</option>
				    <option value="Cerrado">Cerrado</option> 
  				</select> 
  				
       		</div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button class="btn btn-default" type="submit">Guardar Presupuesto</button>
                </div>
            </div>
        </form:form>
    </jsp:body>

</petclinic:layout>
