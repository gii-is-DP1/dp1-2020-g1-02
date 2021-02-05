<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>


<petclinic:layout pageName="Reclamaciones">
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
                    <c:out value="${reclamacion.servicio.lugar}"/>
                </td>
                <td>
                    <c:out value="${reclamacion.servicio.fechainicio}"/>
                </td>
                 <td>
                    <c:out value="${reclamacion.servicio.fechafin}"/>
                </td>
                <td>
                    <c:out value="${reclamacion.servicio.tipocategoria}"/>
                </td>
                <td>
                    <c:out value="${reclamacion.servicio.estado}"/>
                </td>
            </tr>
        </tbody>
    	</table>
       <h2>Reclamacion</h2>
       <form:form modelAttribute="reclamacion" class="form-horizontal" action="/reclamaciones/save">
       		<div class="form-group has-feedback">
       			<petclinic:inputField label="Fecha" name="fecha"  disabled="true"/>
  				<petclinic:inputField label="Descripcion" name="descripcion"/>
  				<input type="hidden" id="cliente" name="cliente" value="${reclamacion.servicio.cliente.id}" />
       		</div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button class="btn btn-default" type="submit">Poner Reclamacion</button>
                </div>
            </div>
        </form:form>
        
        <h3><c:out value="${error}"/></h3>
        
    </jsp:body>

</petclinic:layout>