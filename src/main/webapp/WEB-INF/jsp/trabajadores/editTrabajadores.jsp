<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>


<petclinic:layout pageName="Trabajadores">
       <h2>Nuevo Trabajador</h2>
       <div class="container">
       <form:form modelAttribute="trabajadorForm" class="form-horizontal" id="add-trabajador-form" action="/users/saveTrabajador">
	       <form:errors path = "*" cssClass = "errorblock" element = "div" />
	        <div class="form-group has-feedback">
       			<petclinic:inputField label="Nombre" name="nombre"/>
       			<petclinic:inputField label="Apellidos" name="apellidos"/>
       			<petclinic:inputField label="DNI" name="dni"/>
       			<petclinic:inputField label="Telefono" name="telefono"/>
       			<petclinic:inputField label="Direccion" name="direccion"/>
       			<petclinic:inputField label="Correo" name="correo"/>
       			<petclinic:inputField label="Username" name="username"/>
           		<petclinic:inputPass  label="Contrase�a" name="password" id="psw"/>
	            <div id="message">
				  <h3>La contrase�a debe contener:</h3>
				  <p id="letter" class="invalid">Una letra <b>min�scula</b></p>
				  <p id="capital" class="invalid">Una letra <b>may�scula</b></p>
				  <p id="number" class="invalid">Un <b>n�mero</b></p>
				  <p id="length" class="invalid">M�nimo <b>8 caracteres</b></p>
				</div>
				<petclinic:inputPass  label="Confirme contrase�a" name="retypePassword" id="cpsw"/>
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
	                        <button class="btn btn-default" type="submit" onclick="return Validate()">A�adir Trabajador</button>
	            </div>
	        </div>
    </form:form>
        </div>
</petclinic:layout>
