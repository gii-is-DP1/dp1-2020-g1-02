<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>


<petclinic:layout pageName="Events">
    <jsp:body>
       <h2><c:if test="${producto['new']}">Nuevo</c:if>Producto</h2>
       <div class="container">
       <form:form modelAttribute="producto" class="form-horizontal" action="/productos/save">
       		<div class="form-group has-feedback">
       			<petclinic:inputField label="Nombre" name="name"/>
       			<input type="hidden" name="cantidad" value='<c:out value="0"/>'/>
       		</div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button class="btn btn-default" type="submit">Guardar Producto</button>
                </div>
            </div>
        </form:form>
        </div>
    </jsp:body>

</petclinic:layout>