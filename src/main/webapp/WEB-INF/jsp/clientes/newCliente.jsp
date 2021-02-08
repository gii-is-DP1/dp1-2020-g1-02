<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="clientes">
    <div class="container">
	    <h2> Nuevo Cliente </h2>
    <p>Registrar como:</p>
    <a href="/users/new"><button class="btn btn-default" type="submit">Cliente</button></a>
    <a href="/users/newProveedor"><button class="btn btn-default" type="submit">Proveedor</button></a>
    <form:form modelAttribute="clienteForm" class="form-horizontal" id="add-cliente-form" action="/users/saveCliente">
        <div class="form-group has-feedback">
            <petclinic:inputField label="Nombre" name="nombre"/>
            <petclinic:inputField label="Apellidos" name="apellidos"/>
            <petclinic:inputField label="Telefono" name="telefono"/>
            <petclinic:inputField label="Direccion" name="direccion"/>
            <petclinic:inputField label="Dni" name="dni"/>
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
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                        <button class="btn btn-default" type="submit" onclick="return Validate()">A�adir Cliente</button>
            </div>
        </div>
    </form:form>
    </div>
</petclinic:layout>
