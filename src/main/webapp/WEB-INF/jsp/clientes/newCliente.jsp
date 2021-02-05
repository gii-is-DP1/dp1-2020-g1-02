<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="clientes">
    <h2>
        <c:if test="${cliente['new']}">Nuevo </c:if> Cliente
    </h2>
    <div class="container">
    <p>Registrar como:</p>
    <a href="/users/new"><button class="btn btn-default" type="submit">Cliente</button></a>
    <a href="/users/newProveedor"><button class="btn btn-default" type="submit">Proveedor</button></a>
    <form:form modelAttribute="cliente" class="form-horizontal" id="add-cliente-form" action="/users/saveCliente">
        <div class="form-group has-feedback">
            <petclinic:inputField label="Nombre" name="nombre"/>
            <petclinic:inputField label="Apellidos" name="apellidos"/>
            <petclinic:inputField label="Telefono" name="telefono"/>
            <petclinic:inputField label="Direccion" name="direccion"/>
            <petclinic:inputField label="Dni" name="dni"/>
            <petclinic:inputField label="Correo" name="correo"/>
            <petclinic:inputField label="Username" name="user.username"/>
            <petclinic:inputPass  label="Password" name="user.password"/>
            <petclinic:inputPass  label="Confirma Contrase�a" name="user.retypePassword"/>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${cliente['new']}">
                        <button class="btn btn-default" type="submit">A�adir Cliente</button>
                    </c:when>
                    <c:otherwise>
                        <button class="btn btn-default" type="submit">Actualizar Cliente</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form:form>
    </div>
</petclinic:layout>
