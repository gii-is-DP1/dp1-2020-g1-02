<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ attribute name="name" required="true" rtexprvalue="true"
	description="Name of the active menu: home, owners, vets or error"%>

<c:set var="user" value="${pageContext.request.userPrincipal.name}" />

      <!-- header -->
       <header>
         <!-- header inner -->
         <div class="header">
            <div class="head_top">
               <div class="container">
                  <div class="row">
                    <div class="col-xl-6 col-lg-6 col-md-6 col-sm-12">
                       <div class="top-box">
                        <ul class="sociel_link">
                         <li> <a target="_blank" href="https://www.facebook.com/disanlimpiezas/"><i class="fa fa-facebook"></i></a></li>
                         <li> <a target="_blank" href="https://twitter.com/disan_limpiezas?lang=es"><i class="fa fa-twitter"></i></a></li>
                         <li> <a target="_blank" href="https://www.instagram.com/disanlimpiezas/?hl=es"><i class="fa fa-instagram"></i></a></li>
                     </ul>
                    </div>
                  </div>
                  <div class="col-xl-6 col-lg-6 col-md-6 col-sm-12">
                       <div class="top-box">
                        <p>Disan Limpiezas. Servicio profesional y personalizado </p>
                    </div>
                  </div>
               </div>
            </div>
         </div>
         <div class="">
            <div class="row">
               <div class="col-xl-3 col-lg-3 col-md-3 col-sm-3 col logo_section">
                  <div class="full">
                     <div class="center-desk">
                        <div class="logo"> <a href="index.html"><img src="/resources/images/logo.jpg" alt="logo" width="120px" /></a> </div>
                     </div>
                  </div>
               </div>
               <div class="col-xl-7 col-lg-7 col-md-9 col-sm-9">
                  <div class="menu-area">
                     <div class="limit-box">
                        <nav class="main-menu">
                           <ul class="menu-area-main">
                            
                              <petclinic:menuItem active="${name eq 'home'}" url="/"
					title="home page">
					<span class="glyphicon glyphicon-home" aria-hidden="true"></span>
					<span>Home</span>
				</petclinic:menuItem>
				
				<sec:authorize access="hasAuthority('administrador')">
      				<petclinic:menuItem active="${name eq 'trabajadores'}" url="/trabajadores"
						title="Trabajadores">
						<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
						<span>Trabajadores</span>
					</petclinic:menuItem>
					
					<petclinic:menuItem active="${name eq 'trabsC'}" url="/contratosTrabajadores"
						title="Contratos">
						<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
						<span>Contratos</span>
					</petclinic:menuItem>
					
					<petclinic:menuItem active="${name eq 'servicios'}" url="/servicios"
						title="Servicios">
						<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
						<span>Servicios</span>
					</petclinic:menuItem>
	
					<petclinic:menuItem active="${name eq 'provs'}" url="/proveedores"
						title="Proveedores">
						<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
						<span>Proveedores</span>
					</petclinic:menuItem>
	
					<petclinic:menuItem active="${nombre eq 'clientes'}" url="/clientes"
						title="clientes">
						<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
						<span>Clientes</span>
					</petclinic:menuItem>
					
					<petclinic:menuItem active="${nombre eq 'mensajes'}" url="/mensajes"
						title="Mensajes">
						<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
						<span>Mensajes</span>
					</petclinic:menuItem>

					<petclinic:menuItem active="${name eq 'oferta'}" url="/ofertas"
						title="Ofertas">

						<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
						<span>Ofertas de productos</span>
					</petclinic:menuItem>
					
					<petclinic:menuItem active="${name eq 'producto'}" url="/productos"
						title="Productos">
						<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
						<span>Stock</span>
					</petclinic:menuItem>
					<petclinic:menuItem active="${name eq 'pedidos'}" url="/pedidos"
						title="Pedidos">
						<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
						<span>Pedidos</span>
					</petclinic:menuItem>
					<petclinic:menuItem active="${name eq 'facturas'}" url="/facturas"
						title="Facturas">
						<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
						<span>Facturas</span>
					</petclinic:menuItem>
					<petclinic:menuItem active="${name eq 'morosos'}" url="/administradores/morosos"
						title="Morosos">
						<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
						<span>Morosos</span>
					</petclinic:menuItem>
				</sec:authorize>
				
				<sec:authorize access="hasAuthority('trabajador')">
					<petclinic:menuItem active="${name eq 'registrohoras'}" url="/registroHoras/new"
						title="horario">
						<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
						<span>Registrar Horas</span>
					</petclinic:menuItem>
					<petclinic:menuItem active="${name eq 'horario'}" url="/horarios/misHorarios"
						title="horario">
						<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
						<span>Horario</span>
					</petclinic:menuItem>
				</sec:authorize>
				
				<sec:authorize access="hasAuthority('cliente')">
					<petclinic:menuItem active="${name eq 'servicios'}" url="/servicios/misServicios" title="servicios">
						<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
						<span>Servicios</span>
					</petclinic:menuItem>
					
					<petclinic:menuItem active="${name eq 'contratos'}" url="/contratosServicios/misContratos" title="contratos">
						<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
						<span>Contratos</span>
					</petclinic:menuItem>
					
				</sec:authorize>
				
				<sec:authorize access="hasAuthority('proveedor')">
				
					<petclinic:menuItem active="${name eq 'ofertas'}" url="/ofertas/new"
					title="Ofertas">
					<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
					<span>Ofertar producto</span>
				</petclinic:menuItem>
				
				 <petclinic:menuItem active="${name eq 'facturas'}" url="/facturas/misFacturas"
					title="Facturas">
					<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
					<span>Mis facturas</span>
				</petclinic:menuItem> 
				</sec:authorize>

				<c:if test="${user != null}">
					<li><a href="/users/<c:out value="${user}"/>">Perfil</a></li>
				</c:if>
				<c:if test = "${user == null}">
         				<li><a href="curriculums/new">Enviar curriculum</a></li>
      			</c:if>

                           </ul>
                        </nav>
                     </div>
                  </div>
               </div>
               <div class="col-xl-1 col-lg-1 col-md-1 col-sm-1">
               		<c:if test = "${user == null}">
         				<li><a class="buy" href="/login">Login</a></li>
      				</c:if>
               </div>
               
               <div class="col-xl-1 col-lg-1 col-md-1 col-sm-1">
               		<c:if test = "${user == null}">
         				<li class="buy"> <a href="/users/new">Signup</a> </li>
      				</c:if>
      				<c:if test="${user != null}">
						<li class="buy"> <a href="/logout">Logout</a> </li>
					</c:if>
                  
               </div>
            </div>
         </div>
         <!-- end header inner --> 
      </header>