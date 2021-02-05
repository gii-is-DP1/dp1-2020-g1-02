<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="curriculums"> 
    <jsp:body>
       <div class="container">
       <h2>Currículum de <c:out value="${curriculum.nombre}"/>&nbsp;<c:out value="${curriculum.apellidos}"/></h2>
       <form:form modelAttribute="curriculum" class="form-horizontal">
       		<div class="form-group has-feedback">
       			<petclinic:inputField label="Nombre" name="nombre" disabled="true"/>
       			<petclinic:inputField label="Apellidos" name="apellidos" disabled="true"/>
       			<petclinic:inputField label="Telefono" name="telefono" disabled="true"/>
       			<petclinic:inputField label="Correo" name="correo" disabled="true"/>
       			<petclinic:inputDescripcion label="Descripción" name="descripcion" row="5" cols="30" disabled="true"/>
  				<petclinic:inputField label="Categoría" name="tipocategoria" disabled="true"/>
       		</div>
        </form:form>
        </div>
    </jsp:body>
</petclinic:layout>