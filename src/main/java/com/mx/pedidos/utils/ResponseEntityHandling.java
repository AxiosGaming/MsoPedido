package com.mx.pedidos.utils;

import com.mx.pedidos.config.Folio;
import com.mx.pedidos.constantes.CodigosResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Arrays;
import java.util.List;

/**
 * Metodo de Entidad de respuesta
 *
 * @author NAC2021.
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class ResponseEntityHandling {
	// Folio
	@Autowired
	private Folio folio;

	/**
	 * Handle all exception response entity.
	 *
	 * @param ex      the ex
	 * @param request the request
	 * @return the response entity
	 */
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<CreditoResponseError> handleAllException(Throwable ex, WebRequest request) {

		// Cuando hay una excepción, devuelve un 500
		// Con folio
		// Con información
		// Con detalles
		return new ResponseEntity<>(
				CreditoResponseError.builder().codigo(CodigosResponse.CODIGO_500_CUSTOMIZED.getCodigo())
						.folio(folio.getFolio())
						.mensaje("Internal Server Error")
						.info(CodigosResponse.CODIGO_500_CUSTOMIZED.getCodigo())
						.detalles(Arrays.asList(ex.getMessage())).build(),
				CodigosResponse.CODIGO_500_CUSTOMIZED.getHttpStatus());
	}

	/**
	 * Handle bad request response entity.
	 *
	 * @param ex      the ex
	 * @param request the request
	 * @return the response entity
	 */
	@ExceptionHandler(ExceptionGenerica.class)
	public static final ResponseEntity<CreditoResponseError> handleBadRequest(ExceptionGenerica ex,
			WebRequest request) {
		// Si es ExcepcionGenerica, devuelve el código correspondiente
		// Con folio
		// Con información
		// Con detalles
		return new ResponseEntity<>(
				CreditoResponseError.builder().codigo(ex.getCodigo()).folio(ex.getFolio()).mensaje(ex.getMensaje())
						.info(ex.getCodigo()).detalles(ex.getDetalles()).build(),
				ex.getCodigosRespuesta().getHttpStatus());
	}

	/**
	 * Handle method exception response entity.
	 *
	 * @param ex the ex
	 * @return the response entity
	 */
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<CreditoResponseError> handleMethodArgumentTypeMismatchException(
			MethodArgumentTypeMismatchException ex) {
		String name = ex.getName();
		//Obtiene el tipo
		String type = "";
		if(ex.getRequiredType() != null) {
			type = ex.getRequiredType().getSimpleName();
		}
		Object value = ex.getValue();
		String message = String.format("'%s' deberia ser un '%s' valido  pero '%s' no lo es", name, type, value);
		//Cuando es una MethodArgumentTypeMismatchException devuelve un 400
		//Con folio
		//Con información
		return new ResponseEntity<>(
			CreditoResponseError.builder().codigo(CodigosResponse.CODIGO_400_CUSTOMIZED.getCodigo())
				.folio(folio.getFolio()).mensaje(CodigosResponse.CODIGO_400_CUSTOMIZED.getDescripcion())
				.info(CodigosResponse.CODIGO_400.getCodigo())
				.detalles(Arrays.asList(message)).build(),
			HttpStatus.BAD_REQUEST);
	}

	/**
	 * Handle method exception response entity for missing headers
	 *
	 * @param ex
	 * @return the response entity
	 */
	@ExceptionHandler(MissingRequestHeaderException.class)
	public ResponseEntity<CreditoResponseError> handleHeaders(MissingRequestHeaderException ex) {
		// Se obtiene el nombre del header faltante
		String name = ex.getHeaderName();
		// Se obtienen los detalles
		List<String> detalles = Arrays.asList(String.format(" El header '%s' es requerido ", name), ex.getMessage());
		// Si es un MissingRequestHeaderException, devuelve un 400
		// Con folio
		// Con información
		// Con detalles
		return new ResponseEntity<>(
				CreditoResponseError.builder().codigo(CodigosResponse.CODIGO_400_CUSTOMIZED.getCodigo())
						.folio(folio.getFolio()).mensaje(CodigosResponse.CODIGO_400_CUSTOMIZED.getDescripcion())
						.info(CodigosResponse.CODIGO_400.getCodigo()).detalles(detalles).build(),
				HttpStatus.BAD_REQUEST);
	}
}
