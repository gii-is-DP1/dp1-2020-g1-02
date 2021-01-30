<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>


<petclinic:layout pageName="Events">
    <jsp:body>
       <h2><c:if test="${trabajador['new']}">Nuevo </c:if>Administrador</h2>
       <div class="container">
       <form:form modelAttribute="trabajador" class="form-horizontal" action="/users/actualizarAdministrador">
       		<div class="form-group has-feedback">
       			<input type="hidden" id="id" name="id" value=' <c:out value="${trabajador.id}"/>'>
       			<petclinic:inputField label="Username" name="user.username" disabled="true"/>
       			<petclinic:inputField label="Nombre" name="nombre" disabled="true"/>
       			<petclinic:inputField label="Apellidos" name="apellidos" disabled="true"/>
       			<petclinic:inputField label="DNI" name="dni" disabled="true"/>
       			<petclinic:inputField label="Telefono" name="telefono"/>
       			<petclinic:inputField label="Direccion" name="direccion"/>
       			<petclinic:inputField label="Correo" name="correo"/>
       			<petclinic:inputField label="Categoría" name="tipocategoria" disabled="true"/>
       		</div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button class="btn btn-default" type="submit">Editar perfil</button>
                </div>
            </div>
        </form:form>
        </div>
    </jsp:body>

</petclinic:layout>