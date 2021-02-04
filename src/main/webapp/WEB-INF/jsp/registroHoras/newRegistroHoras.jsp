<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="registroHoras">
 	<jsp:attribute name="customScript">
        <script>
/*             $(function () {
                $("#hora_entrada").datepicker({dateFormat: 'yy/mm/dd HH:mm'});
                $("#hora_salida").datepicker({dateFormat: 'yy/mm/dd HH:mm'});
            }); */
            
            $(function () {
                $('#hora_entrada').datetimepicker({
                    locale: 'es'
                });
                $('#hora_salida').datetimepicker({
                    locale: 'es'
                });
            });
        </script>
    </jsp:attribute>
    <jsp:body> 
	    <h2>
	        <c:if test="${registroHoras['new']}">Nuevo </c:if> Registro Horas
	    </h2>
	    <form:form modelAttribute="registro_horas" class="form-horizontal" id="add-registroHora-form" action="/registroHoras/save">
	        <div class="form-group has-feedback">
		        <!-- <input type="datetime-local" id="hora_entrada" name="hora_entrada" pattern="[0-9]{4}/[0-9]{2}/[0-9]{2}\s[0-9]{2}:[0-9]{2}" > -->
	            <petclinic:inputField label="HoraEntrada" name="hora_entrada"/>   
	            <petclinic:inputField label="HoraSalida" name="hora_salida"/>
	            <input type="hidden" id="trabajador" name="trabajador" value="${trabajador.id}" />
	            
	        </div>
	            <div class="form-group">
	                <div class="col-sm-offset-2 col-sm-10">
	                    <button class="btn btn-default" type="submit">Guardar Horario</button>
	                </div>
	            </div>
	    </form:form>
    </jsp:body> 
</petclinic:layout>
