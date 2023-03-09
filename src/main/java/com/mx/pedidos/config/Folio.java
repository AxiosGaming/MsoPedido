package com.mx.pedidos.config;

import com.mx.pedidos.constantes.Constantes;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.regex.Pattern;

/*
 * Genera un Folio que identifica la solicitud
 *
 * @author TeamScor
 *
 */

@Component
public class Folio {
	/**
	 * Clase folio
	 * validacion de expresion regular
	 * */
	private static final Pattern myRegex = Pattern.compile("[-:T.]");

	/*
	 * Gets folio.
	 *
	 * @return the folio
	 */
	public String getFolio() {

		return Constantes.MSO_ID + "-" + myRegex.matcher(LocalDateTime.now().toString()).replaceAll("") + "00";

	}

}
