<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<spring:url value="/resources/images/star.jpg" var="star"/>
<c:set var="user" value="${pageContext.request.userPrincipal.name}" />

<petclinic:layout pageName="home">
		 <section class="slider_section">
         <div id="main_slider" class="carousel slide banner-main" data-ride="carousel">

            <div class="carousel-inner">
               <div class="carousel-item active">
                  <img class="first-slide" src="/resources/images/banner.jpg" alt="First slide">
                  <div class="container">
                     <div class="carousel-caption relative">
                        <h1>¿Conoces ya<br> <strong class="black_bold">nuestro protocolo </strong><br>
                           <strong class="yellow_bold">covid? </strong></h1>
                        <p>Nuestra empresa ya cuenta con la Certificación de Protocolos frente al COVID-19 de AENOR</p>
                     </div>
                  </div>
               </div>
               <div class="carousel-item">
                  <img class="second-slide" src="/resources/images/banner2.jpg" alt="Second slide">
                  <div class="container">
                     <div class="carousel-caption relative">
                        <h1>Nuestros <br> <strong class="black_bold">últimos Servicios </strong><br>
                           <strong class="yellow_bold">a tu disposición </strong></h1>
                        <p>Seleccione el servicio que usted necesita y configúrelo a su medida ¡No pague de más por algo que no le hace falta para nada!</p>
                        <c:if test = "${user == null}">
         					 <a  href="/login">Ir a los Servicios</a>
	      				</c:if>
	      				<c:if test="${user != null}">
	      					<sec:authorize access="hasAuthority('cliente')">
							 <a  href="/servicios/misServicios">Ir a los Servicios</a>
							</sec:authorize>
							<sec:authorize access="hasAuthority('administrador')">
							 <a  href="/servicios">Ir a los Servicios</a>
							</sec:authorize>
							
						</c:if>
                       
                     </div>
                  </div>
               </div>

            </div>
            <a class="carousel-control-prev" href="#main_slider" role="button" data-slide="prev">
            <i class='fa fa-angle-right'></i>
            </a>
            <a class="carousel-control-next" href="#main_slider" role="button" data-slide="next">
            <i class='fa fa-angle-left'></i>
            </a>
            
         </div>

      </section>



<!-- CHOOSE  -->
      <div class="whyschose">
         <div class="container">

            <div class="row">
               <div class="col-md-7 offset-md-3">
                  <div class="title">
                     <h2>¿Por qué <strong class="black">elegirnos?</strong></h2>
                     <span>Precios competitivos y trato cercano y personalizado a nuestros clientes</span>
                  </div>
               </div>
            </div>
         </div>
      </div>
      
      
      <%-- <c:forEach items="${valoraciones}" var="valoracion">
    <div>
         <div>
            <th style="width: 150px;"><c:out value="${valoracion.key}"/></th>
            
        </div>
         <div>
         	<c:forEach begin="1" step="1" end="${valoracion.value}" >
						<img src="${star}" width="30px"/>
					</c:forEach>
               </div>
 
        
    </div>
    </c:forEach> --%>
      
      
      
      
      <div class="choose_bg">
         <div class="container">
            <div class="white_bg">
            <div class="row">
            
            <c:forEach items="${valoraciones}" var="valoracion" varStatus="index">
				    <div class="col-xl-3 col-lg-3 col-md-6 col-sm-12">
                  		<div class="for_box">
				            <i><img src="resources/icon/${index.index}.png"/></i>
		                    <h3><c:out value="${valoracion.key}"/></h3>
		                    <h4> Valoracion de nuestros clientes</h4>
				            <p> <c:forEach begin="1" step="1" end="${valoracion.value}" >
										<img src="${star}" width="30px"/>
									</c:forEach>
							</p>
				        </div>
				        
   					</div>
    		</c:forEach>
               <!-- <div class="col-xl-3 col-lg-3 col-md-6 col-sm-12">
                  <div class="for_box">
                     <i><img src="resources/icon/1.png"/></i>
                     <h3>Jardinería</h3>
                     <p>Mantenimiento de jardines, corte de césped y poda de setos.</p>
                  </div>
               </div>
               <div class="col-xl-3 col-lg-3 col-md-6 col-sm-12">
                  <div class="for_box">
                     <i><img src="resources/icon/2.png"/></i>
                     <h3>Limpieza</h3>
                     <p>Limpieza y desinfección de todo tipo de instalaciones.</p>
                  </div>
               </div>
               <div class="col-xl-3 col-lg-3 col-md-6 col-sm-12">
                  <div class="for_box">
                     <i><img src="resources/icon/3.png"/></i>
                     <h3>Mantenimiento</h3>
                     <p>Servicios de mantenimiento en casas y edificios. Ej: Electricidad, Fontanería, etc.</p>
                  </div>
               </div>
               <div class="col-xl-3 col-lg-3 col-md-6 col-sm-12">
                  <div class="for_box">
                     <i><img src="resources/icon/4.png"/></i>
                     <h3>Cristalería</h3>
                     <p>Limpieza y mantenimiento de cristales y cristaleras.</p>
                  </div>
               </div> -->
               <!-- <div class="col-md-12">
               <c:if test = "${user == null}">
         					 <a href="/login" class="read-more">Más Servicios</a>
	      				</c:if>
	      				<c:if test="${user != null}">
	      					<sec:authorize access="hasAuthority('cliente')">
							 <a href="/servicios/misServicios" class="read-more">Más Servicios</a>
							</sec:authorize>
							<sec:authorize access="hasAuthority('administrador')">
							  <a href="/servicios" class="read-more">Más Servicios</a>
							</sec:authorize>
							
						</c:if>
            
               </div> -->
            </div>
         </div>
       </div>
      </div>
<!-- end CHOOSE -->

      <!-- service --> 
      <div class="service">
         <div class="container">
            <div class="row">
               <div class="col-md-8 offset-md-2">
                  <div class="title">
                     <h2>Service <strong class="black">Process</strong></h2>
                     <span>Easy and effective way to get your device repair</span>
                  </div>
               </div>
            </div>
            <div class="row">
               <div class="col-xl-4 col-lg-4 col-md-4 col-sm-12">
                  <div class="service-box">
                     <i><img src="resources/icon/service1.png"/></i>
                     <h3>Servicio rápido</h3>
                     <p>Disan es reconocido por su servicio rápido y efectivo</p>
                  </div>
               </div>
               <div class="col-xl-4 col-lg-4 col-md-4 col-sm-12">
                  <div class="service-box">
                     <i><img src="resources/icon/service2.png"/></i>
                     <h3>Pague por lo que necesita</h3>
                     <p>No pierda tiempo y dinero, nuestros peritos le recomendarán el servicio que necesita sin pagar ni un euro de más</p>
                  </div>
               </div>
               <div class="col-xl-4 col-lg-4 col-md-4 col-sm-12">
                  <div class="service-box">
                     <i><img src="resources/icon/service3.png"/></i>
                     <h3>Equipo experto</h3>
                     <p>Nuestro equipo está conformado por peritos y personal con experiencia en el campo de la limpieza y desinfección </p>
                  </div>
               </div>
               <div class="col-xl-4 col-lg-4 col-md-4 col-sm-12">
                  <div class="service-box">
                     <i><img src="resources/icon/service4.png"/></i>
                     <h3>Servicios reconocidos</h3>
                     <p>Nuestros servicios destacan y son elegidos por grandes empresas nacionales e internacionales </p>
                  </div>
               </div>
               <div class="col-xl-4 col-lg-4 col-md-4 col-sm-12">
                  <div class="service-box">
                     <i><img src="resources/icon/service5.png"/></i>
                     <h3>30 días de prueba</h3>
                     <p>Si no estás  agusto con nuestros servicios puedes cancelarlos los primeros 30 días despues de haber firmado el contrato </p>
                  </div>
               </div>
               <div class="col-xl-4 col-lg-4 col-md-4 col-sm-12">
                  <div class="service-box">
                     <i><img src="resources/icon/service6.png"/></i>
                     <h3>Procesos reconocidos</h3>
                     <p>Nuestra empresa cuenta con varias certificaciones referentes a la limpieza y desinfección de instalaciones </p>
                  </div>
               </div>
            </div>
         </div>
      </div>
      <!-- end service -->
	
</petclinic:layout>
