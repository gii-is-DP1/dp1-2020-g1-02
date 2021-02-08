<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<petclinic:layout pageName="Profile">
       <div class="container">
			<sec:authorize access="hasAuthority('administrador')">
				<div class="row">
					<h2>¡Bienvenido! administrador <c:out value="${administrador.user.username}"></c:out></h2>
				</div>
				<form:form modelAttribute="administrador" class="form-horizontal" action="/users/actualizarAdministrador">
	       		<div class="form-group has-feedback">
	       			<input type="hidden" id="id" name="id" value=' <c:out value="${administrador.id}"/>'>
	       			<petclinic:inputField label="Username" name="user" disabled="true"/>
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
			</sec:authorize>
			
			<sec:authorize access="hasAuthority('trabajador')">
				<div class="row">
					<h2>¡Bienvenido! trabajador <c:out value="${trabajador.user.username}"></c:out></h2>
				</div>
				<form:form modelAttribute="trabajador" class="form-horizontal" action="/users/actualizarTrabajador">
	       		<div class="form-group has-feedback">
	       			<input type="hidden" id="id" name="id" value=' <c:out value="${trabajador.id}"/>'>
	       			<petclinic:inputField label="Username" name="user" disabled="true"/>
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
			</sec:authorize>
			
			<sec:authorize access="hasAuthority('cliente')">
				<div class="row">
					<h2>¡Bienvenido! cliente <c:out value="${cliente.user.username}"></c:out></h2>
				</div>
				<form:form modelAttribute="cliente" class="form-horizontal" action="/users/actualizarCliente">
	       		<div class="form-group has-feedback">
	       			<input type="hidden" id="id" name="id" value=' <c:out value="${cliente.id}"/>'>
	       			<petclinic:inputField label="Username" name="user" disabled="true"/>
	       			<petclinic:inputField label="Nombre" name="nombre" disabled="true"/>
	       			<petclinic:inputField label="Apellidos" name="apellidos" disabled="true"/>
	       			<petclinic:inputField label="DNI" name="dni" disabled="true"/>
	       			<petclinic:inputField label="Telefono" name="telefono"/>
	       			<petclinic:inputField label="Direccion" name="direccion"/>
	       			<petclinic:inputField label="Correo" name="correo"/>
	       		</div>
	            <div class="form-group">
	                <div class="col-sm-offset-2 col-sm-10">
	                    <button class="btn btn-default" type="submit">Editar perfil</button>
	                </div>
	            </div>
	        	</form:form>
			</sec:authorize>
			
			<sec:authorize access="hasAuthority('proveedor')">
				<div class="row">
					<h2>¡Bienvenido! proveedor <c:out value="${proveedor.user.username}"></c:out></h2>
				</div>
				<form:form modelAttribute="proveedor" class="form-horizontal" action="/users/actualizarProveedor">
	       		<div class="form-group has-feedback">
	       			<input type="hidden" id="id" name="id" value=' <c:out value="${proveedor.id}"/>'>
	       			<petclinic:inputField label="Username" name="user" disabled="true"/>
	       			<petclinic:inputField label="Nombre de Empresa" name="name" disabled="true"/>
	       			<petclinic:inputField label="Telefono" name="telefono"/>
	       			<petclinic:inputField label="Direccion" name="direccion"/>
	       			<petclinic:inputField label="Correo" name="email"/>
	       		</div>
	            <div class="form-group">
	                <div class="col-sm-offset-2 col-sm-10">
	                    <button class="btn btn-default" type="submit">Editar perfil</button>
	                </div>
	            </div>
	        	</form:form>
			</sec:authorize>
			<div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <a href="/users/editContrasenya"><button class="btn btn-default">Cambiar contraseña</button></a>
                </div>
            </div>
			
        </div>
</petclinic:layout>