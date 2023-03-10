package com.mx.pedidos;


import com.mx.pedidos.constantes.CodigosResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CodigosResponseTest {
  /**
   * test
   * OrqAuto
   * pruebas unitarias
   * */
  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testWhenCodigoExistsThenReturnsCode() {
    CodigosResponse cod = CodigosResponse.getByCode("200");
    assertEquals(CodigosResponse.CODIGO_200, cod, "Los códigos no coinciden");

    cod = CodigosResponse.getByCode("400");
    assertEquals(CodigosResponse.CODIGO_400, cod, "Los códigos no coinciden");

    cod = CodigosResponse.getByCode("401");
    assertEquals(CodigosResponse.CODIGO_401, cod, "Los códigos no coinciden");

    cod = CodigosResponse.getByCode("404");
    assertEquals(CodigosResponse.CODIGO_404, cod, "Los códigos no coinciden");

    cod = CodigosResponse.getByCode("500");
    assertEquals(CodigosResponse.CODIGO_500, cod, "Los códigos no coinciden");

  }

  @Test
  void testWhenCodigoDoesNotExistThenReturns500() {
    CodigosResponse cod = CodigosResponse.getByCode("403");
    assertEquals(CodigosResponse.CODIGO_500, cod, "Los códigos no coinciden");

    cod = CodigosResponse.getByCode("503");
    assertEquals(CodigosResponse.CODIGO_500, cod, "Los códigos no coinciden");
  }

  @Test
  void testSetCodigo() {

    assertEquals(CodigosResponse.CODIGO_400_CUSTOMIZED.getCodigo(), CodigosResponse.CODIGO_400_CUSTOMIZED.getCustomizedCodigo400("400").getCodigo(), "Los códigos no coinciden");
    assertEquals(CodigosResponse.CODIGO_500_CUSTOMIZED.getCodigo(), CodigosResponse.CODIGO_500_CUSTOMIZED.getCustomizedCodigo500("500").getCodigo(), "Los códigos no coinciden");
  }

}