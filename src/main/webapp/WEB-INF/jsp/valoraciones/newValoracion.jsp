<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>


<petclinic:layout pageName="valoraciones">
    <jsp:body>
    
    <h2>Servicio seleccionado</h2>
        <table id="serviciosTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;">Lugar</th>
            <th style="width: 150px;">Fecha Inicio</th>
            <th style="width: 150px;">Fecha Fin</th>
            <th style="width: 150px;">Tipo Categoria</th>
             <th style="width: 150px;">Estado</th>
        </tr>
        </thead>
        <tbody>
            <tr>
                <td>
                    <c:out value="${valoracion.servicio.lugar}"/>
                </td>
                <td>
                    <c:out value="${valoracion.servicio.fechainicio}"/>
                </td>
                 <td>
                    <c:out value="${valoracion.servicio.fechafin}"/>
                </td>
                <td>
                    <c:out value="${valoracion.servicio.tipocategoria}"/>
                </td>
                <td>
                    <c:out value="${valoracion.servicio.estado}"/>
                </td>
            </tr>
        </tbody>
    	</table>
    
       <h2>Poner una valoracion</h2>
       <form:form modelAttribute="valoracion" class="form-horizontal" action="/valoraciones/save">
       		<div class="form-group has-feedback">
       			<petclinic:inputField label="Fecha" name="fecha"  disabled="true"/>
  				<label for="nivelsatisfaccion">Nivel de Satisfaccion</label>
  				<select id="nivelsatisfaccion" name="nivelsatisfaccion">
    				<option value="MuyAlto">Muy Alto</option>
				    <option value="Alto">Alto</option>
				    <option value="Medio">Medio</option>
				    <option value="Bajo">Bajo</option>
				    <option value="MuyBajo">Muy Bajo</option>
  				</select>
  				<input type="hidden" id="cliente" name="cliente" value="${valoracion.servicio.cliente.id}" />
  				<input type="hidden" id="servicio" name="servicio" value="${valoracion.servicio.id}" />
       		</div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button class="btn btn-default" type="submit">Realizar Valoracion</button>
                </div>
            </div>
        </form:form>
        
        <h3><c:out value="${error}"/></h3>
        
    </jsp:body>

</petclinic:layout>