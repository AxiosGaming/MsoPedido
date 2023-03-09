package com.mx.pedidos.security;

import com.mx.pedidos.constantes.Constantes;
import lombok.AllArgsConstructor;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Configuración del RequestMatcher
 * 
 * @author hcholula
 *orqauto
 */
@EnableWebSecurity
@AllArgsConstructor
public class RequestMatcherConfig implements RequestMatcher {
	//Agregamos los endpoints que queremos excluir del CSRF
	//Health
	//Controller
	//Y los que se consuman
	private final List<AntPathRequestMatcher> requestMatchers = Arrays
			.asList(new AntPathRequestMatcher(Constantes.URL_PATH+Constantes.MONITOR),
				new AntPathRequestMatcher(Constantes.URL_PATH+Constantes.CONSULTA_PEDIDO),
					new AntPathRequestMatcher(Constantes.URL_PATH+Constantes.CREACION_PEDIDO),
					new AntPathRequestMatcher(Constantes.URL_PATH+Constantes.ACTUALIZAR_PEDIDO),
					new AntPathRequestMatcher(Constantes.URL_PATH+Constantes.CANCELAR_PEDIDO),
					new AntPathRequestMatcher(Constantes.URL_PATH+Constantes.TOKEN));

	/**
	 * Método para validar si la request hace match
	 * 
	 * @param request
	 * @return true/false
	 * 
	 */
	@Override
	public boolean matches(HttpServletRequest request) {
		//Busca en la lista de matchers alguno que coincida con el endpoint que se quiere consumir
		Optional<AntPathRequestMatcher> rmOpt = requestMatchers
				.parallelStream().filter(rm -> rm.matches(request)).findFirst();
		//Si lo encuentra, entonces está excluido, por lo que devuelve false
		//Si no lo encuentra, no está excluido, por lo que devuelve true y, por consecuencia, 403
		return !rmOpt.isPresent();
	}

}
