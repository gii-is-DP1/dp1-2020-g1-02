<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="instalaciones">
    <h2>
        <c:if test="${instalacion['new']}">Nueva </c:if> Instalacion
    </h2>
    <form:form modelAttribute="instalacion" class="form-horizontal" id="add-cliente-form" action="/instalaciones/save">
        <div class="form-group has-feedback">
       	    <input type="hidden" id="cliente" name="cliente" value=' <c:out value="${cliente.id}"/>'>
            <petclinic:inputField label="Lugar" name="lugar"/>
            <petclinic:inputField label="Dimension" name="dimension"/>
        </div>
        	<div class="form-group">
	                <div class="col-sm-offset-2 col-sm-10">
	                    <button class="btn btn-default" type="submit">Guardar Instalacion</button>
	                </div>
	        </div>
    </form:form>
</petclinic:layout>
