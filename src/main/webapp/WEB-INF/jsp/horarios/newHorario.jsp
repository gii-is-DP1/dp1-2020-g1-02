<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="horario">
    <h2>
        <c:if test="${horarios['new']}">Nuevo </c:if> Horario
    </h2>
    <form:form modelAttribute="horario" class="form-horizontal" id="add-cliente-form" action="/horarios/save">
        <div class="form-group has-feedback">
       	    <%-- <label for="trabajador">Nombre del trabajador</label>
  				<select id="trabajador" name="trabajador">
  				<c:forEach items="${trabajadores}" var="trabajador">
		            <tr>
		                <td>
		                   <option><c:out value="${trabajador.nombre}"/></option>
		            </tr>
		        </c:forEach>
  				</select> --%>
  			<petclinic:selectField label="Nombre Trabajador" name="trabajador" size="${size}" names="${trabajadores}"></petclinic:selectField>
            <petclinic:inputField label="HoraInicio" name="hora_inicio"/>
            <petclinic:inputField label="HoraFin" name="hora_fin"/>
            <petclinic:inputField label="Descripcion" name="descripcion"/>
        </div>
        <div class="form-group">
	                <div class="col-sm-offset-2 col-sm-10">
	                    <button class="btn btn-default" type="submit">Añadir Horario</button>
	                </div>
	            </div>
    </form:form>
    
    <h3><c:out value="${error}"/></h3>
    
</petclinic:layout>