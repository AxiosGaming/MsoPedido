package com.mx.pedidos.controller;

import com.mx.pedidos.config.Folio;
import com.mx.pedidos.constantes.Constantes;
import com.mx.pedidos.dao.InterfaceGestionPedidos;
import com.mx.pedidos.models.request.Pedido;
import com.mx.pedidos.models.request.IdPedido;
import com.mx.pedidos.models.response.ServiceResponse;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controlador para el alta
 * busqueda, actualizacion de los datos
 * y eliminacion de un pedido
 * */
@RestController
public class GestionPedidosController {
    @Autowired
    private InterfaceGestionPedidos interfaceGestionPedidos;
    @Autowired
    private Folio folio;
    @PostMapping(Constantes.CREACION_PEDIDO)
    public ResponseEntity<ServiceResponse> altaPedido(@RequestBody Pedido pedido){
        Boolean resultado =interfaceGestionPedidos.altaPedido(pedido);
        ServiceResponse serviceResponse= ServiceResponse.builder()
                .mensaje(Constantes.OPERACION_EXITOSA)
                .folio(folio.getFolio())
                .resultado(resultado)
                .build();
        return new ResponseEntity<>(serviceResponse, HttpStatus.CREATED);
    }

    @PostMapping(Constantes.CONSULTA_PEDIDO)
    public ResponseEntity<ServiceResponse> consultaPedido(@RequestBody IdPedido idpedido){
        Pedido resultado= interfaceGestionPedidos.consultaPedido(idpedido.getIdPedido());
        ServiceResponse serviceResponse= ServiceResponse.builder()
                .mensaje(Constantes.OPERACION_EXITOSA)
                .folio(folio.getFolio())
                .resultado(resultado)
                .build();
        return new ResponseEntity<>(serviceResponse, HttpStatus.OK);
    }

    @PostMapping(Constantes.ACTUALIZAR_PEDIDO)
    public ResponseEntity<ServiceResponse> actualizarPedido(@RequestBody Pedido pedido){
        Boolean resultado =interfaceGestionPedidos.actualizarPedido(pedido);
        ServiceResponse serviceResponse= ServiceResponse.builder()
                .mensaje(Constantes.OPERACION_EXITOSA)
                .folio(folio.getFolio())
                .resultado(resultado)
                .build();
        return new ResponseEntity<>(serviceResponse, HttpStatus.CREATED);
    }

    @PostMapping(Constantes.CANCELAR_PEDIDO)
    public ResponseEntity<ServiceResponse> cancelarPedido(@RequestBody IdPedido pedido){
        Boolean resultado=interfaceGestionPedidos.cancelarPedido(pedido.getIdPedido());
        ServiceResponse serviceResponse= ServiceResponse.builder()
                .mensaje(Constantes.OPERACION_EXITOSA)
                .folio(folio.getFolio())
                .resultado(resultado)
                .build();
        return new ResponseEntity<>(serviceResponse, HttpStatus.CREATED);
    }

    @PostMapping(Constantes.TOKEN)
    public String Token(){

        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId("softtekJWT")
                .setSubject("username")
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return "Bearer " +token;
    }



}
