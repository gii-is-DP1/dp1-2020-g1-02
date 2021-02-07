<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<petclinic:layout pageName="Profile">
       <div class="container">
			<form method="POST" action="/users/updatePassword">
			    <div class="form-group has-feedback">
			    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			    <petclinic:inputPass2 label="Introduzca su contraseña" id="oldpass"/>
			    <petclinic:inputPass2 label="Introduzca su nueva contraseña" id="psw"/>
			    <div id="message">
				  <h3>La contraseña debe contener:</h3>
				  <p id="letter" class="invalid">Una letra <b>minúscula</b></p>
				  <p id="capital" class="invalid">Una letra <b>mayúscula</b></p>
				  <p id="number" class="invalid">Un <b>número</b></p>
				  <p id="length" class="invalid">Mínimo <b>8 caracteres</b></p>
				</div>
			    <petclinic:inputPass2 label="Confirme su nueva contraseña" id="cpsw"/>              
			    </div>
			                 
			   <div class="form-group">
		            <div class="col-sm-offset-2 col-sm-10">
		                        <button class="btn btn-default" type="submit" onclick="return Validate()">Confirmar</button>
		            </div>
		        </div>
			
			</form>
        </div>
</petclinic:layout>