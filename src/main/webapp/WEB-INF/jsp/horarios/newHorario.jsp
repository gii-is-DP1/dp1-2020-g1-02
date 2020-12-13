<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="horarios">
    <h2>
        <c:if test="${horario['new']}">Nuevo </c:if> Horario
    </h2>
    <form:form modelAttribute="horario" class="form-horizontal" id="add-cliente-form" action="/horarios/save">
        <div class="form-group has-feedback">
       	    <input type="hidden" id="id" name="id" value=' <c:out value="${trabajador.id}"/>'>
            <petclinic:inputField label="HoraInicio" name="horaInicio"/>
            <petclinic:inputField label="HoraFin" name="horaFin"/>
            <petclinic:inputField label="Descripcion" name="descripcion"/>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${horario['new']}">
                        <button class="btn btn-default" type="submit">Añadir Horario</button>
                    </c:when>
                    <c:otherwise>
                        <button class="btn btn-default" type="submit">Actualizar Horario</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form:form>
</petclinic:layout>