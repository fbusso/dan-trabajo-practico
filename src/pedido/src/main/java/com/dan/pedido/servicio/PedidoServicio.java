package com.dan.pedido.servicio;

import com.dan.pedido.dominio.DetallePedido;
import com.dan.pedido.dominio.Pedido;
import com.dan.pedido.excepcion.ReglaDeNegociosExcepcion;

import java.util.List;
import java.util.Optional;

public interface PedidoServicio {

    Pedido crear(Pedido pedido) throws ReglaDeNegociosExcepcion;

    Pedido confirmar(Integer id) throws ReglaDeNegociosExcepcion;

    Optional<Pedido> obtenerPorId(Integer id);

    List<Pedido> obtenerTodos();

    Optional<Pedido> agregarDetalle(Integer id, DetallePedido detalle);

    void eliminarPorId(Integer id);

    List<Pedido> obtenerPorClienteId(Integer clienteId);

}
