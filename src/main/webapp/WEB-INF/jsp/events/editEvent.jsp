<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>


<petclinic:layout pageName="Events">
    <jsp:body>
       <h2>Event</h2>
       <form:form modelAttribute="event" class="form-horizontal" action="/events/save">
       		<div class="form-group has-feedback">
       			<petclinic:inputField label="Name" name="name"/>
       			<petclinic:inputField label="Apellidos" name="apellidos"/>
       			<petclinic:inputField label="dni" name="dni"/>
       			
       		</div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <input type="hidden" name="id" value="${event.id}"/>
                    <button class="btn btn-default" type="submit">Save Event</button>
                </div>
            </div>
        </form:form>
    </jsp:body>

</petclinic:layout>
