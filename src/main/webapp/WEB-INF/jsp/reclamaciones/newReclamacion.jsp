<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>


<petclinic:layout pageName="Reclamaciones">
 	<jsp:attribute name="customScript">
        <script>
            $(function () {
                $("#fecha").datepicker({dateFormat: 'yy/mm/dd'});
            });
        </script>
    </jsp:attribute>
    <jsp:body>
       <h2>Poner una reclamacion</h2>
       <form:form modelAttribute="reclamacion" class="form-horizontal" action="/reclamaciones/save">
       		<div class="form-group has-feedback">
       			<petclinic:inputField label="Fecha del Servicio" name="fecha"/>
  				<petclinic:inputField label="Descripcion" name="descripcion"/>
  				<label for="cliente">Nombre del Cliente</label>
  				<select id="cliente" name="cliente">
  				<c:forEach items="${clientes}" var="cliente">
		            <tr>
		                <td>
		                   <option><c:out value="${cliente.nombre}"/></option>
		            </tr>
		        </c:forEach>
  				</select>
  				<label for="servicio">ID del Servicio</label>
  				<select id="servicio" name="servicio">
  				<c:forEach items="${servicios}" var="servicio">
		            <tr>
		                <td>
		                   <option><c:out value="${servicio.id}"/></option>
		            </tr>
		        </c:forEach>
  				</select>
       		</div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button class="btn btn-default" type="submit">Poner Reclamacion</button>
                </div>
            </div>
        </form:form>
    </jsp:body>

</petclinic:layout>