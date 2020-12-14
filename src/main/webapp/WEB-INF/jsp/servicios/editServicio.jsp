<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>


<petclinic:layout pageName="Events">
 <jsp:attribute name="customScript">
        <script>
            $(function () {
                $("#fechainicio").datepicker({dateFormat: 'yy/mm/dd'});
                $("#fechafin").datepicker({dateFormat: 'yy/mm/dd'});
            });
        </script>
    </jsp:attribute>
    <jsp:body>
       <h2>Servicio</h2>
       <form:form modelAttribute="servicio" class="form-horizontal" action="/servicios/save">
       		<div class="form-group has-feedback">
       			<input type="hidden" id="id" name="id" value=' <c:out value="${servicio.id}"/>'>
       			<input type="hidden" name="estado" value="Espera"/>
       			<petclinic:inputField label="Lugar" name="lugar"/>
       			<petclinic:inputField label="Fecha Inicial" name="fechainicio"/>
       			<petclinic:inputField label="Fecha Final" name="fechafin"/>
       			<label for="tipocategoria">Categoria</label>
  				<select id="tipocategoria" name="tipocategoria">
    				<option value="Limpieza">Limpieza</option>
				    <option value="Mantenimiento">Mantenimiento</option>
				    <option value="Lavandería">Lavandería</option>
  				</select>
  				
       		</div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button class="btn btn-default" type="submit">Guardar Servicio</button>
                </div>
            </div>
        </form:form>
    </jsp:body>

</petclinic:layout>
