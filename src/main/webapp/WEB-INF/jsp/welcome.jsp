<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<!-- %@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %-->  

<petclinic:layout pageName="home">
    <h2><fmt:message key="welcome"/></h2>
    
		<div class="row">
		<div class="col-6"><img src="/resources/images/inicio.jpg" width="500px" height="400px"></div>
		<div class="col-6"><p>
		Somos una empresa creada en los momentos econ�micos m�s dif�ciles posibles. En nuestra andadura hemos sabido crecer aunando precios competitivos y trato cercano y personalizado a nuestros clientes. En estos momentos seguimos manteniendo una l�nea de crecimiento sostenido que nos ha permitido aumentar nuestra plantilla de trabajadores en m�s del 50% en el �ltimo a�o. Actualmente nuestro �mbito de trabajo se establece en las provincias de Huelva, Sevilla y C�diz, queriendo ampliar nuestros trabajos de forma inminente al resto de provincias andaluzas y sur de Extremadura.</p></div>
		</div>
	
</petclinic:layout>
