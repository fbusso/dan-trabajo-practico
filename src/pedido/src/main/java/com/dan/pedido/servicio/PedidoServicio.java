package com.dan.pedido.servicio;

import com.dan.pedido.dominio.DetallePedido;
import com.dan.pedido.dominio.Pedido;

import java.util.Optional;

public interface PedidoServicio {

    Pedido crear(Pedido pedido);

    Optional<Pedido> obtenerPorId(Integer id);

    Optional<Pedido> agregarDetalle(Integer id, DetallePedido detalle);

    void eliminarPorId(Integer id);
}
