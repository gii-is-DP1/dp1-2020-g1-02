<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<spring:url value="/trabajadores/{trabajadorId}/update" var="editTrabajador">
	<spring:param name="trabajadorId" value="${trabajador.id}"/>
</spring:url>
<petclinic:layout pageName="Trabajadores">
       <div class="container">
       <h2>Actualizar Trabajador</h2>
       <form:form modelAttribute="trabajador" class="form-horizontal" id="add-trabajador-form" action="${fn:escapeXml(editTrabajador)}">
	        <div class="form-group has-feedback">
	        	<input type="hidden" id="id" name="id" value='<c:out value="${trabajador.id}"/>'/>
       			<petclinic:inputField label="Nombre" name="nombre"/>
       			<petclinic:inputField label="Apellidos" name="apellidos"/>
       			<petclinic:inputField label="DNI" name="dni"/>
       			<petclinic:inputField label="Telefono" name="telefono"/>
       			<petclinic:inputField label="Direccion" name="direccion"/>
       			<petclinic:inputField label="Correo" name="correo"/>
       			<petclinic:inputField label="Username" name="user" disabled="true"/>
				<label for="tipocategoria">Categoria</label>
  				<select name="tipocategoria">
    				<option value="Limpieza">Limpieza</option>
				    <option value="Mantenimiento">Mantenimiento</option>
				    <option value="Cristaleria">Cristaleria</option>
				    <option value="Jardineria">Jardineria</option>
  				</select>
	        </div>
	        <div class="form-group">
	            <div class="col-sm-offset-2 col-sm-10">
	                        <button class="btn btn-default" type="submit" onclick="return Validate()">Confirmar cambios</button>
	            </div>
	        </div>
    </form:form>
        </div>
</petclinic:layout>
