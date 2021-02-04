<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="curriculums">
    <h2>
        <c:if test="${curriculums['new']}">Nuevo </c:if> Curriculum
    </h2>
    <form:form modelAttribute="curriculums" class="form-horizontal" id="add-curriculums-form" action="/curriculums/save">
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
            <label for="tipocategoria">Categoria</label>
  				<select id="tipocategoria" name="tipocategoria">
    				<option value="Limpieza">Limpieza</option>
				    <option value="Mantenimiento">Mantenimiento</option>
				    <option value="Cristaleria">Cristaleria</option>
				    <option value="Jardineria">Jardineria</option>
  				</select>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${curriculums['new']}">
                        <button class="btn btn-default" type="submit">Añadir Curriculum</button>
                    </c:when>
                </c:choose>
            </div>
        </div>
    </form:form>
</petclinic:layout>