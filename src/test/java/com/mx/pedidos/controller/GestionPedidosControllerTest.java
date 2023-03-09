package com.mx.pedidos.controller;

import com.mx.pedidos.config.Folio;
import com.mx.pedidos.dao.InterfaceGestionPedidos;
import com.mx.pedidos.models.request.IdPedido;
import com.mx.pedidos.models.request.Pedido;
import com.mx.pedidos.models.response.ServiceResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class GestionPedidosControllerTest {
    @Mock
    private InterfaceGestionPedidos interfaceGestionPedidos;
    @Mock
    private Folio folio;

    @InjectMocks
    private GestionPedidosController gestionPedidosController;

    private Pedido pedido;


    private IdPedido idPedido;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.pedido= Pedido.builder()
                .idPedido("ABC123")
                .datos("LENOVO")
                .build();
        when(folio.getFolio()).thenReturn("PER0000.0000");
        this.idPedido= IdPedido.builder()
                .idPedido("ABC123v")
                .build();
    }

    @Test
    void testAltaPedido() {
        when(interfaceGestionPedidos.altaPedido(pedido)).thenReturn(Boolean.TRUE);
        ResponseEntity<ServiceResponse> respuestaEsperada=gestionPedidosController.altaPedido(pedido);
        assertNotNull(respuestaEsperada.getStatusCode(),"No deberia ser nulo");
    }

    @Test
    void testConsultaPedido() {
        when(interfaceGestionPedidos.consultaPedido(pedido.getIdPedido())).thenReturn(pedido);
        ResponseEntity<ServiceResponse> respuestaEsperada=gestionPedidosController.consultaPedido(idPedido);
        assertNotNull(respuestaEsperada.getStatusCode());
    }

    @Test
    void testActualizarPedido() {
        when(interfaceGestionPedidos.actualizarPedido(pedido)).thenReturn(Boolean.TRUE);
        ResponseEntity<ServiceResponse> respuestaEsperada=gestionPedidosController.actualizarPedido(pedido);
        assertNotNull(respuestaEsperada.getStatusCode());
    }

    @Test
    void testCancelarPedido() {
        when(interfaceGestionPedidos.cancelarPedido(pedido.getIdPedido())).thenReturn(Boolean.TRUE);
        ResponseEntity<ServiceResponse> respuestaEsperada=gestionPedidosController.cancelarPedido(idPedido);
        assertNotNull(respuestaEsperada.getStatusCode());
    }
}