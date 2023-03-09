package com.mx.pedidos.constantes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * codigos de respuesta
 *
 * @author TeamScore
 */
@Getter
@AllArgsConstructor
public enum CodigosResponse {
	//Código 200: OK
	CODIGO_200(String.valueOf(HttpStatus.OK.value()), Constantes.OPERACION_EXITOSA, HttpStatus.OK),
	/* Código 201: Creado */
	CODIGO_201(String.valueOf(HttpStatus.CREATED.value()), Constantes.OPERACION_EXITOSA, HttpStatus.CREATED),

	/* Código 400: Bad Request */
	CODIGO_400(String.valueOf(HttpStatus.BAD_REQUEST.value()), Constantes.BAD_REQUEST,
			HttpStatus.BAD_REQUEST),

	/* Código 401: Unauthorized */
	CODIGO_401(String.valueOf(HttpStatus.UNAUTHORIZED.value()), Constantes.UNAUTHORIZED,
			HttpStatus.UNAUTHORIZED),

	/* Código 404: Not Found */
	CODIGO_404(String.valueOf(HttpStatus.NOT_FOUND.value()), Constantes.NOT_FOUND,
			HttpStatus.NOT_FOUND),

	/* Código 500: Internal Server Error */
	CODIGO_500(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()),
			HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), HttpStatus.INTERNAL_SERVER_ERROR),

	/* Código 500 personalizado */
	CODIGO_500_CUSTOMIZED(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()),
			HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), HttpStatus.INTERNAL_SERVER_ERROR),

	/* Código 400 personalizado */
	CODIGO_400_CUSTOMIZED(String.valueOf(HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST.getReasonPhrase(),
			HttpStatus.BAD_REQUEST);
	;

	private String codigo;
	private String descripcion;
	private HttpStatus httpStatus;

	/*
	 * Constructor de la clase
	 */
	CodigosResponse() {

	}

	/**
	 * Obtener Código
	 *
	 * @return codigo en formato string
	 */
	public String getCodigo() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(this.getHttpStatus().value());
		stringBuilder.append(".");
		stringBuilder.append(Constantes.MSO_ID);
		stringBuilder.append(".");
		stringBuilder.append(Constantes.MSO_ID);
		stringBuilder.append(".");
		stringBuilder.append(codigo);
		return stringBuilder.toString();
	}

	/**
	 * Set código
	 *
	 * @param codigo
	 */
	private void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * Gets by code.
	 *
	 * @param codigo the codigo
	 * @return the by code
	 */
	public static CodigosResponse getByCode(String codigo) {
		for (CodigosResponse code : CodigosResponse.values()) {
			if (code.codigo.equals(codigo)) {
				return code;
			}
		}
		return CODIGO_500;
	}

	/**
	 * codigos respuesta
	 *
	 * @param codigo tipo de codigo
	 * @return error 500
	 */
	public CodigosResponse getCustomizedCodigo500(String codigo) {
		CodigosResponse error = CODIGO_500_CUSTOMIZED;
		error.setCodigo(codigo);
		return error;
	}

	/**
	 * codigos respuesta
	 *
	 * @param codigo tipo de codigo
	 * @return Error 400
	 */
	public CodigosResponse getCustomizedCodigo400(String codigo) {
		CodigosResponse error = CODIGO_400_CUSTOMIZED;
		error.setCodigo(codigo);
		return error;
	}

}
