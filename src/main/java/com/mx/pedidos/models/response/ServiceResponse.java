package com.mx.pedidos.models.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * Datos de la operación
 *
 * @author Angel Godinez
 *
 * */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServiceResponse {
	
	//Mensaje del resultado
	private String mensaje;
	
	//Folio asignado a la operación
	private String folio;
	
	//Resultado de la operación
	private Object resultado;
}
