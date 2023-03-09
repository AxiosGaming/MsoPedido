package com.mx.pedidos.dao.impl;

import com.mx.pedidos.config.Folio;
import com.mx.pedidos.config.SpringConfiguration;
import com.mx.pedidos.constantes.CodigosResponse;
import com.mx.pedidos.constantes.Constantes;
import com.mx.pedidos.dao.InterfaceGestionPedidos;
import com.mx.pedidos.models.request.Pedido;
import com.mx.pedidos.utils.ExceptionGenerica;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



@Repository
public class GestionPedidoServiceImpl implements InterfaceGestionPedidos {

    private PreparedStatement statement;
    /* Folio */
    @Autowired
    private Folio folio;
    @Override
    public Boolean altaPedido(Pedido pedido) {
        String sql ="INSERT INTO TABPED (STRIDPEDI,STRINF,BOOLSTATUS) VALUES(?,?,?);";
        Boolean informacionPedido=existePedido(pedido.getIdPedido());
        if(Boolean.TRUE.equals(informacionPedido)) {
            throw ExceptionGenerica.builder().codigosRespuesta(CodigosResponse.CODIGO_400).detalles(Constantes.DATO_EXISTENTE)
                    .folio(folio.getFolio()).build();
        }
        try{
            AbstractApplicationContext ctx =
                    new AnnotationConfigApplicationContext(SpringConfiguration.class);

            DataSource dataSource = ctx.getBean("dataSource", DataSource.class);

            Connection connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1,pedido.getIdPedido());
            statement.setString(2,pedido.getDatos());
            statement.setInt(3, Constantes.ESTATUS);
            int rs = statement.executeUpdate();
            if(rs!=0){
                return Boolean.TRUE;
            }

        }
        catch (Exception e){
            throw ExceptionGenerica.builder().codigosRespuesta(CodigosResponse.CODIGO_500).detalles(Constantes.ERROR_INTERNAL_SERVER)
                    .folio(folio.getFolio()).build();
        }
        return Boolean.TRUE;
    }

    @Override
    public Pedido consultaPedido(String idPedido) {
        String sql ="SELECT * FROM  TABPED WHERE STRIDPEDI=? AND BOOLSTATUS=1";
        Pedido p = new Pedido();
        try{
            AbstractApplicationContext ctx =
                    new AnnotationConfigApplicationContext(SpringConfiguration.class);

            DataSource dataSource = ctx.getBean("dataSource", DataSource.class);

            Connection connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1,idPedido);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                p.setIdPedido(rs.getString("STRIDPEDI"));
                p.setDatos(rs.getString("STRINF"));
            }
            ctx.close();
        }
        catch (Exception e){
            throw ExceptionGenerica.builder().codigosRespuesta(CodigosResponse.CODIGO_500).detalles(Constantes.ERROR_INTERNAL_SERVER)
                    .folio(folio.getFolio()).build();
        }

        if(p.getIdPedido()==null){
            throw ExceptionGenerica.builder().codigosRespuesta(CodigosResponse.CODIGO_404).detalles(Constantes.NOT_FOUND)
                    .folio(folio.getFolio()).build();
        }
        return p;
    }

    @Override
    public Boolean actualizarPedido(Pedido pedido) {
        String sql ="UPDATE TABPED  SET STRINF=? WHERE STRIDPEDI=?;";
        Pedido informacionPedido=consultaPedido(pedido.getIdPedido());
        try{
            AbstractApplicationContext ctx =
                    new AnnotationConfigApplicationContext(SpringConfiguration.class);

            DataSource dataSource = ctx.getBean("dataSource", DataSource.class);

            Connection connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1,pedido.getDatos());
            statement.setString(2,pedido.getIdPedido());
            int rs = statement.executeUpdate();
            if(rs!=0){
                return Boolean.TRUE;
            }
        }
        catch (Exception e){
            throw ExceptionGenerica.builder().codigosRespuesta(CodigosResponse.CODIGO_500).detalles(Constantes.ERROR_INTERNAL_SERVER)
                    .folio(folio.getFolio()).build();
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean cancelarPedido(String pedido) {
        Pedido informacionPedido=consultaPedido(pedido);
        String sql="UPDATE TABPED  SET BOOLSTATUS=? WHERE STRIDPEDI=?;";
        try{
            AbstractApplicationContext ctx =
                    new AnnotationConfigApplicationContext(SpringConfiguration.class);

            DataSource dataSource = ctx.getBean("dataSource", DataSource.class);

            Connection connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1,Constantes.ESTATUS_CERO);
            statement.setString(2,pedido);
            int rs = statement.executeUpdate();
            if(rs!=0){
                return Boolean.TRUE;
            }

        }
        catch (Exception e){
            throw ExceptionGenerica.builder().codigosRespuesta(CodigosResponse.CODIGO_500).detalles(Constantes.ERROR_INTERNAL_SERVER)
                    .folio(folio.getFolio()).build();
        }
        return Boolean.TRUE;
    }

    public boolean existePedido(String idPedido){
        String sql ="SELECT * FROM  TABPED WHERE STRIDPEDI=?";
        Pedido p = new Pedido();
        try{
            AbstractApplicationContext ctx =
                    new AnnotationConfigApplicationContext(SpringConfiguration.class);

            DataSource dataSource = ctx.getBean("dataSource", DataSource.class);

            Connection connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1,idPedido);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                p.setIdPedido(rs.getString("STRIDPEDI"));
                p.setDatos(rs.getString("STRINF"));
            }
            ctx.close();
        }
        catch (Exception e){
            throw ExceptionGenerica.builder().codigosRespuesta(CodigosResponse.CODIGO_500).detalles(Constantes.ERROR_INTERNAL_SERVER)
                    .folio(folio.getFolio()).build();
        }
        if(p.getIdPedido()==null){
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

}
