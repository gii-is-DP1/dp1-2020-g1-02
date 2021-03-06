package org.springframework.samples.petclinic.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author japarejo
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/resources/**","/webjars/**","/h2-console/**").permitAll()
				
				.antMatchers(HttpMethod.GET, "/","/error").permitAll()
				.antMatchers("/users/new").permitAll()
				
				.antMatchers("/users/newTrabajador").hasAuthority("administrador")
				
				.antMatchers("/users/newAdministrador").hasAuthority("administrador")
				
				.antMatchers("/users/saveTrabajador").hasAuthority("administrador")
				
				.antMatchers("/users/saveAdministrador").hasAuthority("administrador")
				
				.antMatchers("/users/**").hasAnyAuthority("administrador", "cliente", "proveedor")
				
				.antMatchers("/clientes/**").hasAuthority("administrador")
				
				.antMatchers("/reclamaciones/new/**").hasAnyAuthority("cliente")
				
				.antMatchers("/reclamaciones/save").hasAnyAuthority("cliente")
				
				.antMatchers("/reclamaciones/**").hasAnyAuthority("administrador")
				
				.antMatchers("/registroHoras/new").hasAnyAuthority("trabajador")
				
				.antMatchers("/registroHoras/save").hasAnyAuthority("trabajador")
				
				.antMatchers("/registroHoras/**").hasAnyAuthority("administrador")
				
				.antMatchers("/administradores/**").hasAnyAuthority("administrador")
				
				.antMatchers("/instalaciones/misInstalaciones").hasAuthority("cliente")
				
				.antMatchers("/instalaciones/new").hasAuthority("cliente")
				
				.antMatchers("/instalaciones/save").hasAuthority("cliente")
				
				.antMatchers("/instalaciones/**").permitAll()
				
				.antMatchers("/horarios/misHorarios").hasAnyAuthority("trabajador")
				
				.antMatchers("/horarios/**").hasAnyAuthority("administrador")
				
				.antMatchers("/contratos").hasAnyAuthority("administrador")
				
				.antMatchers("/contratosServicios/misContratos").hasAuthority("cliente")
				
				.antMatchers("/valoraciones/misValoraciones").hasAuthority("cliente")
				
				.antMatchers("/contratosServicios/**").hasAuthority("administrador")
				
				.antMatchers("/curriculums/new").permitAll()
				
				.antMatchers("/curriculums/save").permitAll()
				
				.antMatchers("/curriculums/**").hasAnyAuthority("administrador")
				
				.antMatchers("/proveedores/**").hasAnyAuthority("administrador")
				
				.antMatchers("/valoraciones/new").hasAnyAuthority("cliente")
				.antMatchers("/valoraciones/**").permitAll()
				
				.antMatchers("/facturas/misFacturas").hasAnyAuthority("proveedor")
				.antMatchers("/facturas/**").hasAnyAuthority("administrador")
				
				.antMatchers("/pedidos/**").hasAnyAuthority("administrador")

				.antMatchers("/ofertas/new").hasAnyAuthority("proveedor")
				.antMatchers("/ofertas/save").hasAnyAuthority("proveedor")
				
				.antMatchers("/ofertas/**").hasAnyAuthority("administrador")
				
				.antMatchers("/productos/**").hasAnyAuthority("administrador")

				.antMatchers("/trabajadores/**").hasAnyAuthority("administrador")

				.antMatchers("/contratosTrabajadores/**").hasAnyAuthority("administrador")

				.antMatchers("/servicios/misServicios").hasAuthority("cliente")
				
				.antMatchers("/servicios/**").hasAnyAuthority("administrador", "cliente")
				
				.antMatchers("/mensajes/**").hasAnyAuthority("administrador", "cliente", "trabajador", "proveedor")
				
				.antMatchers("/users/**").permitAll()
				
				.antMatchers("/authorities/**").permitAll()

				.antMatchers("/presupuestos/**").permitAll()
				.antMatchers("/admin/**").hasAnyAuthority("admin")
				.anyRequest().denyAll()
				.and()
				 	.formLogin()
				 	/*.loginPage("/login")*/
				 	.failureUrl("/login-error")
				.and()
					.logout()
						.logoutSuccessUrl("/"); 
                // Configuración para que funcione la consola de administración 
                // de la BD H2 (deshabilitar las cabeceras de protección contra
                // ataques de tipo csrf y habilitar los framesets si su contenido
                // se sirve desde esta misma página.
                http.csrf().ignoringAntMatchers("/h2-console/**");
                http.headers().frameOptions().sameOrigin();
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
	      .dataSource(dataSource)
	      .usersByUsernameQuery(
	       "select username,password,enabled from users where username = ?")
	      .authoritiesByUsernameQuery(
	       "select username, authority "
	        + "from authorities "
	        + "where username = ?")	   	      
	      .passwordEncoder(passwordEncoder());	
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
	
}


