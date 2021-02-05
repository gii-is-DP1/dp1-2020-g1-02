<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="curriculums"> 
    <jsp:body>
       <div class="container">
       <h2>Envía tu curriculum para trabajar con nosotros</h2>
       <form:form modelAttribute="curriculum" class="form-horizontal" action="/curriculums/save">
       		<div class="form-group has-feedback">
       			<petclinic:inputField label="Nombre" name="nombre"/>
       			<petclinic:inputField label="Apellidos" name="apellidos"/>
       			<petclinic:inputField label="Telefono" name="telefono"/>
       			<petclinic:inputField label="Correo" name="correo"/>
       			<petclinic:inputDescripcion label="Descripción" name="descripcion" row="5" cols="30"/>
  				<form:select path="tipocategoria">
				   <form:options/>
				</form:select>
       		</div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button class="btn btn-default" type="submit">Envíar Currículum</button>
                </div>
            </div>
        </form:form>
        </div>
    </jsp:body>
</petclinic:layout>