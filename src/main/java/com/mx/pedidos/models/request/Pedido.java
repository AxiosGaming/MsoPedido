package com.mx.pedidos.models.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pedido {
    /**
     * clase correspondiente al modelo
     * del pedido
     * */
    //identificador del pedido
    private String idPedido;
    //nombre del pedido
    private String datos;
}
