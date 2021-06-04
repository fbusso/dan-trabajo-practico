package com.dan.pedido.servicio;

import com.dan.pedido.dominio.DetallePedido;
import com.dan.pedido.dominio.Pedido;
import com.dan.pedido.excepcion.ReglaDeNegociosExcepcion;

import java.util.Optional;

public interface PedidoServicio {

    Pedido crear(Pedido pedido) throws ReglaDeNegociosExcepcion;

    Optional<Pedido> obtenerPorId(Integer id);

    Optional<Pedido> agregarDetalle(Integer id, DetallePedido detalle);

    void eliminarPorId(Integer id);
}
