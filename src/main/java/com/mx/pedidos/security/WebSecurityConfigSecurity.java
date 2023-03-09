package com.mx.pedidos.security;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.header.writers.StaticHeadersWriter;
import org.springframework.security.web.util.matcher.RequestMatcher;


/**
 * Configuración de seguridad Web
 * 
 * @author hcholula
 *orqauto
 */
@EnableWebSecurity
@NoArgsConstructor
@AllArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(1)
public class WebSecurityConfigSecurity extends WebSecurityConfigurerAdapter {
	//RequestMatcher para CSRF
	@Autowired
	private RequestMatcher csrfRequestMatcher;

	/**
	 * Configuraciones de seguridad
	 * 
	 * @param http
	 * @throws Exception
	 * 
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//CSP
		//default-src contiene varias configuraciones de la política
		//Self es para aceptar cualquier origen
		http.headers().contentSecurityPolicy("default-src 'self'").reportOnly();
		//Expect-CT
		//max-age es la cantidad de segundos después de la recepción del campo de encabezado Expect-CT
		//durante los cuales se debe considerar el host del mensaje recibido como un host Expect-CT conocido
		//enforce indica que se debe hacer cumplir la política de Transparencia de certificados
		//y que se deben rechazar futuras conexiones que violen su política de Transparencia de certificados
		http.headers().addHeaderWriter(new StaticHeadersWriter("Expect-CT", "max-age=3600, enforce"));
		//Habilita el CSRF sólo para las request que hagan match

		http.csrf().requireCsrfProtectionMatcher(csrfRequestMatcher);

	}

}
