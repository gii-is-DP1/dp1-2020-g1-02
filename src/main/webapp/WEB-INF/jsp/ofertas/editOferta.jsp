<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="ofertas">
    <h2>
        <c:if test="${oferta['new']}">Nueva </c:if> Oferta
    </h2>
    <form:form modelAttribute="oferta" class="form-horizontal" id="add-oferta-form" action="/ofertas/save">
        <div class="form-group has-feedback">
        
           <%--<petclinic:inputField label="Nombre" name="name"/> --%>  
           
           <label for="name">Producto a ofertar</label>
  				<select id="name" name="name">
  				<c:forEach items="${productos}" var="producto">
		            <tr>
		                <td>
		                   <option><c:out value="${producto.name}"/></option>
		                </td>
		            </tr>
		        </c:forEach>
  				</select>
            <petclinic:inputField label="PrecioUnidad" name="precioU"/>
            <input type="hidden" id="proveedor" name="proveedor" value="${proveedor.id}" />
            
        </div>
       	<div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button class="btn btn-default" type="submit">Añadir oferta</button>
                </div>
        </div>
    </form:form>
</petclinic:layout>