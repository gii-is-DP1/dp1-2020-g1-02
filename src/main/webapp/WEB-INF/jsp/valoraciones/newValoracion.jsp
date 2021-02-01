<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>


<petclinic:layout pageName="valoraciones">
 	<jsp:attribute name="customScript">
        <script>
            $(function () {
                $("#fecha").datepicker({dateFormat: 'yy/mm/dd'});
            });
        </script>
    </jsp:attribute>
    <jsp:body>
       <h2>Poner una valoracion</h2>
       <form:form modelAttribute="valoracion" class="form-horizontal" action="/valoraciones/save">
       		<div class="form-group has-feedback">
       			<petclinic:inputField label="Fecha del Servicio" name="fecha"/>
  				<label for="nivelsatisfaccion">Nivel de Satisfaccion</label>
  				<select id="nivelsatisfaccion" name="nivelsatisfaccion">
    				<option value="MuyAlto">Muy Alto</option>
				    <option value="Alto">Alto</option>
				    <option value="Medio">Medio</option>
				    <option value="Bajo">Bajo</option>
				    <option value="MuyBajo">Muy Bajo</option>
  				</select>
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
                    <button class="btn btn-default" type="submit">Realizar Valoracion</button>
                </div>
            </div>
        </form:form>
    </jsp:body>

</petclinic:layout>