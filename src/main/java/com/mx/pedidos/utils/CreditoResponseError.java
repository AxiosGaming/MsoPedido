package com.mx.pedidos.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/* *
 * Clase de respuesta de error
 *
 * @author Angel Godinez
 *
 * */
@Data
@AllArgsConstructor
@Builder
public class CreditoResponseError {

	// Código de error
	private String codigo;
	// Mensaje de error
	private String mensaje;
	// Folio del error
	private String folio;
	// Información del error
	private String info;
	// Detalles del error
	private List<String> detalles;

}
