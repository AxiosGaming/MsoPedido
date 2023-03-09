package com.mx.pedidos.dao;

import com.mx.pedidos.models.request.Pedido;
import org.springframework.stereotype.Repository;


public interface InterfaceGestionPedidos {
    Boolean altaPedido(Pedido pedido);

    Pedido consultaPedido(String idPedido);

    Boolean actualizarPedido(Pedido pedido);

    Boolean cancelarPedido(String pedido);
}
