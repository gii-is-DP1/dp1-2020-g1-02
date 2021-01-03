<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!--  >%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%-->
<%@ attribute name="name" required="true" rtexprvalue="true"
	description="Name of the active menu: home, owners, vets or error"%>

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
                         <li> <a href="#"><i class="fa fa-facebook-f"></i></a></li>
                         <li> <a href="#"><i class="fa fa-twitter"></i></a></li>
                         <li> <a href="#"><i class="fa fa-instagram"></i></a></li>
                         <li> <a href="#"><i class="fa fa-linkedin"></i></a></li>
                     </ul>
                    </div>
                  </div>
                  <div class="col-xl-6 col-lg-6 col-md-6 col-sm-12">
                       <div class="top-box">
                        <p>long established fact that a reader will be </p>
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
				
				<petclinic:menuItem active="${name eq 'oferta'}" url="/ofertas/new"
					title="contacto">
					<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
					<span>Contacto</span>
				</petclinic:menuItem>

				<petclinic:menuItem active="${name eq 'error'}" url="/oups"
					title="trigger a RuntimeException to see how it is handled">
					<span class="glyphicon glyphicon-warning-sign" aria-hidden="true"></span>
					<span>Error</span>
				</petclinic:menuItem>
                              <li class="mean-last"> <a href="#contact">signup</a> </li>
                           </ul>
                        </nav>
                     </div>
                  </div>
               </div>
               <div class="col-xl-2 col-lg-2 col-md-2 col-sm-2">
                  <li><a class="buy" href="/login">Login</a></li>
               </div>
            </div>
         </div>
         <!-- end header inner --> 
      </header>