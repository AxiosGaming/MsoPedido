package com.mx.pedidos;


import com.mx.pedidos.config.Folio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;

class FolioTest {
  /**
   *
   * OrqAuto Mod
   * pruebas unitarias
   * test
   * */
  @InjectMocks
  private Folio folio;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testGetFolio() throws NoSuchFieldError, IllegalStateException {
    String folioString = folio.getFolio();

    assertThat(folioString).isNotNull();
  }
}