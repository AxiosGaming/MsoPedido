package com.mx.pedidos.utils;


import com.mx.pedidos.constantes.CodigosResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Arrays;
import java.util.List;

/**
 * excepciones genéricas
 *
 * @author TeamScore
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class ExceptionGenerica extends RuntimeException{
	
	/*
	 * Variables para enviar información de la excepción
	 * Código
	 * Mensaje
	 * Folio
	 * Información
	 * Detalles
	 * Código de respuesta
	 */
	
	private static final long serialVersionUID = 1L;
	private final String codigo;
	private final String mensaje;
	private final String folio;
	private final String info;
	private final List<String> detalles;
	private final CodigosResponse codigosRespuesta;
	
	/**
	 * Guarda el valor de las excepciones genéricas
	 *
	 * @param folio            folio
	 * @param info             informacion de la excepcion
	 * @param detalles         detalles de la excepcion
	 * @param codigosRespuesta codigos response
	 */
	@Builder()
	public ExceptionGenerica(String folio, String info, String detalles, CodigosResponse codigosRespuesta) {
		super();
		this.folio = folio;
		this.info = info;
		this.detalles = Arrays.asList(detalles);
		this.codigosRespuesta = codigosRespuesta;
		this.codigo = codigosRespuesta.getCodigo();
		this.mensaje = codigosRespuesta.getDescripcion();
	}
}
