<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<c:set var="user" value="${pageContext.request.userPrincipal.name}" />
<c:set var="admin" value="admin" />
<petclinic:layout pageName="facturas">
    <h2>Facturas</h2>
    
    
    
    
    <c:if test = "${user == admin}">
    <h3> Filtrar por nombre del proveedor: </h3>
        <form action="/facturas/filtrado"> 
		   	<input type="text" name="nameProv" value="${filtrado}"> 
		   	<button type="submit"> Filtrar </button>
    	</form>
     </c:if>
    

    <table id="facturasTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;">Fecha</th>
            <th style="width: 150px;">Precio total</th>
            <th style="width: 150px;">Nombre del proveedor</th>
            <th style="width: 150px;">ID del pedido</th>


        </tr>
        </thead>
        <tbody>
	    	<c:forEach items="${facturas}" var="factura"> 
    	<tr>
              <td>
                  <fmt:parseDate value="${factura.fecha}" pattern="yyyy-MM-dd" var="date"/>
	               <fmt:formatDate value="${date}" type = "date" dateStyle="long"/>  
              </td>
              <td>
                  <c:out value="${factura.precio_total}"/>
              </td>
              <td>
                  <c:out value="${factura.proveedor.name}"/>
              </td>
              <td>
                  <c:out value="${factura.pedido.id}"/>
              </td>
          </tr>
     		</c:forEach>
            
        </tbody>
    </table>
</petclinic:layout>