<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="registroHoras">
    <h2>
        <c:if test="${registroHoras['new']}">Nuevo </c:if> Registro Horas
    </h2>
    <form:form modelAttribute="registro_horas" class="form-horizontal" id="add-registroHora-form" action="/registroHoras/save">
        <div class="form-group has-feedback">
       	     <label for="trabajador">Nombre del trabajador</label>
  				<select id="trabajador" name="trabajador">
  				<c:forEach items="${trabajadores}" var="trabajador">
		            <tr>
		                <td>
		                   <option><c:out value="${trabajador.nombre}"/></option>
		            </tr>
		        </c:forEach>
  				</select>
            <petclinic:inputField label="HoraEntrada" name="hora_entrada"/>
            <petclinic:inputField label="HoraSalida" name="hora_salida"/>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${registroHoras['new']}">
                        <button class="btn btn-default" type="submit">Añadir Registro De Horas</button>
                    </c:when>
                </c:choose>
            </div>
        </div>
    </form:form>
</petclinic:layout>
