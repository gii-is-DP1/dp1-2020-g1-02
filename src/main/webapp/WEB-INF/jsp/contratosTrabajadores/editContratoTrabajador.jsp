<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>


<petclinic:layout pageName="ContratosTrabajadores">
    <jsp:body>
       <h2>Contrato Trabajador</h2>
       <form:form modelAttribute="contratoTrabajador" class="form-horizontal" action="/contratosTrabajadores/save">
       		<div class="form-group has-feedback">
       			<petclinic:inputField label="FechaInicial" name="fechainicial"/>
       			<petclinic:inputField label="FechaFinal" name="fechafinal"/>
       			<input type="number" name="idtrabajador" />
       		<%-- 	<petclinic:inputField label="IdTrabajador" name="idtrabajador"/> --%>
       			<petclinic:inputField label="Sueldo" name="sueldo"/>
       			
       			
       		</div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <input type="hidden" name="id" value="${contratoTrabajador.id}"/>
                    <button class="btn btn-default" type="submit">Save Contrato</button>
                </div>
            </div>
        </form:form>
    </jsp:body>

</petclinic:layout>
