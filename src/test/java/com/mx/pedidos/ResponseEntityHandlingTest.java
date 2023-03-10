package com.mx.pedidos;


import com.mx.pedidos.config.Folio;
import com.mx.pedidos.constantes.CodigosResponse;
import com.mx.pedidos.controller.GestionPedidosController;
import com.mx.pedidos.utils.CreditoResponseError;
import com.mx.pedidos.utils.ExceptionGenerica;
import com.mx.pedidos.utils.ResponseEntityHandling;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Validator;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;


import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class ResponseEntityHandlingTest {
  /**
   * test
   * OrqAuto
   * pruebas unitarias
   * */
  @Mock
  private Folio folio;


  @InjectMocks
  private ResponseEntityHandling handler;

  private static final String FOLIO = "SPEI000001-20220914092935100";
  private static final List<String> DETALLES_ERROR = Arrays.asList("Los siguentes atributos son invalidos y/o requeridos: , idTipoSolicitud")
    .stream().sorted().collect(Collectors.toList());

  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);
    when(folio.getFolio()).thenReturn(FOLIO);
  }

  @Test
  void testHandleAllException() {
    ResponseEntity<CreditoResponseError> respuestaEsperada = new ResponseEntity<>(CreditoResponseError.builder().codigo(CodigosResponse.CODIGO_500_CUSTOMIZED.getCodigo())
      .folio(FOLIO).mensaje(CodigosResponse.CODIGO_500_CUSTOMIZED.getDescripcion())
      .info(CodigosResponse.CODIGO_500_CUSTOMIZED.getCodigo())
      .detalles(Arrays.asList("Error")).build(), CodigosResponse.CODIGO_500_CUSTOMIZED.getHttpStatus());

    Throwable ex = new Throwable("Error");
    ResponseEntity<CreditoResponseError> respuestaObtenida = handler.handleAllException(ex, null);
    assertEquals(respuestaEsperada.getBody(), respuestaObtenida.getBody(), "Throwable, las respuestas no coinciden");
    assertEquals(respuestaEsperada.getStatusCode(), respuestaObtenida.getStatusCode(), "Throwable, los códigos no coinciden");
  }

  @Test
  void testHandleBadRquest() {
    ResponseEntity<CreditoResponseError> respuestaEsperada = new ResponseEntity<>(CreditoResponseError.builder().codigo(CodigosResponse.CODIGO_400.getCodigo()).folio(FOLIO)
      .mensaje(CodigosResponse.CODIGO_400.getDescripcion()).info(CodigosResponse.CODIGO_400.getCodigo()).detalles(DETALLES_ERROR)
      .build(), CodigosResponse.CODIGO_400.getHttpStatus());

    ExceptionGenerica ex = ExceptionGenerica.builder().codigosRespuesta(CodigosResponse.CODIGO_400).folio(FOLIO)
      .detalles(DETALLES_ERROR.get(0)).build();
    ResponseEntity<CreditoResponseError> respuestaObtenida = handler.handleBadRequest(ex, null);
    assertEquals(respuestaEsperada.getBody(), respuestaObtenida.getBody(), "Throwable, las respuestas no coinciden");
    assertEquals(respuestaEsperada.getStatusCode(), respuestaObtenida.getStatusCode(), "Throwable, los códigos no coinciden");
  }

  @Test
  void testHandleMethodArgumentTypeMismatchException() {
    ResponseEntity<CreditoResponseError> respuestaEsperada = new ResponseEntity<>(CreditoResponseError.builder().codigo(CodigosResponse.CODIGO_400_CUSTOMIZED.getCodigo())
      .folio(folio.getFolio()).mensaje(CodigosResponse.CODIGO_400_CUSTOMIZED.getDescripcion())
      .info(CodigosResponse.CODIGO_400.getCodigo())
      .detalles(Arrays.asList("'idPlataforma' deberia ser un 'Integer' valido  pero '-1' no lo es")).build(), HttpStatus.BAD_REQUEST);
    Method method = Arrays.asList(GestionPedidosController.class.getMethods()).parallelStream().filter(m -> m.getName().equals("altaPedido")).findAny().orElse(null);

    MethodArgumentTypeMismatchException ex = new MethodArgumentTypeMismatchException(-1, Integer.class, "idPlataforma", new MethodParameter(method, 0), new Throwable("Error"));
    ResponseEntity<CreditoResponseError> respuestaObtenida = handler.handleMethodArgumentTypeMismatchException(ex);
    assertEquals(respuestaEsperada.getBody(), respuestaObtenida.getBody(), "Las respuestas no coinciden");
    assertEquals(respuestaEsperada.getStatusCode(), respuestaObtenida.getStatusCode(), "Los códigos no coinciden");

    ResponseEntity<CreditoResponseError> respuestaEsperadaNull = new ResponseEntity<>(CreditoResponseError.builder().codigo(CodigosResponse.CODIGO_400_CUSTOMIZED.getCodigo())
      .folio(folio.getFolio()).mensaje(CodigosResponse.CODIGO_400_CUSTOMIZED.getDescripcion())
      .info(CodigosResponse.CODIGO_400.getCodigo())
      .detalles(Arrays.asList("'idPlataforma' deberia ser un '' valido  pero '-1' no lo es")).build(), HttpStatus.BAD_REQUEST);

    MethodArgumentTypeMismatchException exNull = new MethodArgumentTypeMismatchException(-1, null, "idPlataforma", new MethodParameter(method, 0), new Throwable("Error"));
    ResponseEntity<CreditoResponseError> respuestaObtenidaNull = handler.handleMethodArgumentTypeMismatchException(exNull);
    assertEquals(respuestaEsperadaNull.getBody(), respuestaObtenidaNull.getBody(), "Las respuestas no coinciden");
    assertEquals(respuestaEsperadaNull.getStatusCode(), respuestaObtenidaNull.getStatusCode(), "Los códigos no coinciden");
  }
}