package com.dan.pedido.servicio.impl;

import com.dan.pedido.dominio.DetallePedido;
import com.dan.pedido.dominio.EstadoPedido;
import com.dan.pedido.dominio.Pedido;
import com.dan.pedido.repositorio.PedidoRepositorio;
import com.dan.pedido.servicio.PedidoServicio;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class PedidoServicioImpl implements PedidoServicio {

    private final PedidoRepositorio pedidoRepositorio;

    public PedidoServicioImpl(PedidoRepositorio pedidoRepositorio) {
        this.pedidoRepositorio = pedidoRepositorio;
    }

    @Override
    public Pedido crear(Pedido pedido) {
        pedido.setFechaPedido(LocalDate.now());
        pedido.setEstadoPedido(EstadoPedido.NUEVO);
        return pedidoRepositorio.save(pedido);
    }

    @Override
    public Optional<Pedido> obtenerPorId(Integer id) {
        return pedidoRepositorio.findById(id);
    }

    @Override
    public Optional<Pedido> agregarDetalle(Integer id, DetallePedido detalle) {
        Optional<Pedido> pedidoOptional = pedidoRepositorio.findById(id);
        pedidoOptional.ifPresent(pedido -> {
            pedido.getDetallePedido().add(detalle);
            pedidoRepositorio.save(pedido);
        });

        return pedidoOptional;
    }

    @Override
    public void eliminarPorId(Integer id) {
        pedidoRepositorio.deleteById(id);
    }
}
