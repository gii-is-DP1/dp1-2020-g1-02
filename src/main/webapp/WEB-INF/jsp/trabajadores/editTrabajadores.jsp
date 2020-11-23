<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>


<petclinic:layout pageName="Events">
    <jsp:body>
       <h2>Trabajador</h2>
       <form:form modelAttribute="trabajador" class="form-horizontal" action="/trabajadores/save">
       		<div class="form-group has-feedback">
       			<input type="hidden" id="id" name="id" value=' <c:out value="${trabajador.id}"/>'>
       			<petclinic:inputField label="Name" name="name"/>
       			<petclinic:inputField label="Apellidos" name="apellidos"/>
       			<petclinic:inputField label="DNI" name="dni"/>
       			<petclinic:inputField label="Telefono" name="telefono"/>
       			<petclinic:inputField label="Direccion" name="direccion"/>
       			<petclinic:inputField label="Correo" name="correo"/>
       			<label for="tipocategoria">Categoria</label>
  				<select id="tipocategoria" name="tipocategoria">
    				<option value="Limpieza">Limpieza</option>
				    <option value="Mantenimiento">Mantenimiento</option>
				    <option value="Lavandería">Lavandería</option>
  				</select>
       		</div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button class="btn btn-default" type="submit">Guardar Trabajador</button>
                </div>
            </div>
        </form:form>
    </jsp:body>

</petclinic:layout>
