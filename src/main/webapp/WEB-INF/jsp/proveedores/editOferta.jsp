<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="oferta">
	
	<h2> Contactar </h2>

	<div class="formulario">

     	    

            <form id="contacto" action="" method="post" >
           
                


                <label>Tipo de consulta:</label>
                  <select class="input-group" name="tipo" required>
                      <option value="" hidden selected disabled>Seleccion el tipo del inmueble</option>
                      <option value="oferta">Oferta</option>
                      <option value="servicio">Solicitud de servicio</option>
                      <option value="otro">Otro</option>
                  </select>
             
             
             	<label for="name">Nombre: </label>
                <input class="input-group" id="name" name="name" type="text" size="60" placeholder="Nombre" required />
                <br />
                
                <label for="email">E-mail: </label>
                <input class="input-group" id="email" name="email" type="text" size="60" required />
                <br />
                
                <label for="numero">Telefono: </label>
                <input class="input-group" id="numero" name="numero" type="text" size="9" width="300px" required />
                <br />

                <label for="asunto">Nombre del producto: </label>
                <input class="input-group" id="asunto" name="asunto" type="text" size="60" required />
                <br />
                
                <label for="mensaje">Precio por unidad(en euros): </label>
                <input class="input-group" id="mensaje" name="mensaje"  type="text" required />
                <br />
                
             </form>
             
             <input class="boton" type="submit" value="Enviar" />

        </div>


</petclinic:layout>