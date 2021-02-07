<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ attribute name="id" required="true" rtexprvalue="true"
              description="id para validación con js" %>
<%@ attribute name="label" required="false" rtexprvalue="true"
              description="Label appears in red color if input is considered as invalid after submission" %>

    <c:set var="cssGroup" value="form-group ${status.error ? 'has-error' : '' }"/>
    <c:set var="valid" value="${not status.error and not empty status.actualValue}"/>
    <div class="${cssGroup}">
        <label class="col-sm-6 control-label">${label}</label>

        <div class="col-sm-10">
        	 <c:if test="${id != 'oldpass'}">
                 <input class="form-control" type="password" name="${id}" id="${id}" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" title="Debe tener al menos 8 caracteres, así como contener como mínimo una letra minúscula, otra mayúscula y un número" required/>
           
            </c:if>
            <c:if test="${id == 'oldpass'}">
                 <input class="form-control" type="password" name="${id}" id="${id}" title="Debe tener al menos 8 caracteres, así como contener como mínimo una letra minúscula, otra mayúscula y un número" required/>
            </c:if>
            <c:if test="${valid}">
                <span class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true"></span>
            </c:if>
            <c:if test="${status.error}">
                <span class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true"></span>
                <span class="help-inline">${status.errorMessage}</span>
            </c:if>
        </div>
    </div>

