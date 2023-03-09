package com.mx.pedidos.dao.impl;

import com.mx.pedidos.config.Folio;
import com.mx.pedidos.config.SpringConfiguration;
import com.mx.pedidos.models.request.IdPedido;
import com.mx.pedidos.models.request.Pedido;
import com.mysql.cj.conf.PropertyKey;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.test.context.ContextConfiguration;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = SpringConfiguration.class)
class GestionPedidoServiceImplTest {

    @InjectMocks
    private GestionPedidoServiceImpl gestionPedidoService;
    @Mock
    private Folio folio;

    @Mock
    private AbstractApplicationContext abstractApplicationContext;

    private Pedido pedido;

    private IdPedido idPedido;
    private Connection connection;
    @MockBean
    private DataSource dataSource;
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
    void testAltaPedido() throws SQLException {
        //when(dataSource.getConnection()).thenReturn(connection);
        //Boolean respuestaEsperada=gestionPedidoService.altaPedido(pedido);
        //assertNotNull(respuestaEsperada);
    }

    @Test
    void testConsultaPedido() {
    }

    @Test
    void testActualizarPedido() {
    }

    @Test
    void testCancelarPedido() {
    }

    @Test
    void testExistePedido() {
    }
}