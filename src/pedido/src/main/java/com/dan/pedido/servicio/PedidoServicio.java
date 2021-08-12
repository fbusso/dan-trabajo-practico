package com.dan.pedido.servicio;

import com.dan.pedido.dominio.DetallePedido;
import com.dan.pedido.dominio.Pedido;
import com.dan.pedido.excepcion.ReglaDeNegociosExcepcion;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface PedidoServicio {

    Pedido crear(Pedido pedido) throws ReglaDeNegociosExcepcion;

    Pedido confirmar(Integer id) throws ReglaDeNegociosExcepcion;

    Optional<Pedido> obtenerPorId(Integer id);

    Page<Pedido> obtenerTodos(Integer pagina, Integer cantidadRegistros);

    Optional<Pedido> agregarDetalle(Integer id, DetallePedido detalle);

    void eliminarPorId(Integer id);

    List<Pedido> obtenerPorClienteId(Integer clienteId);

}
