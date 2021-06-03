package com.dan.pedido.servicio.impl;

import com.dan.pedido.repositorio.DetallePedidoRepositorio;
import com.dan.pedido.servicio.DetallePedidoServicio;
import org.springframework.stereotype.Service;

@Service
public class DetallePedidoServicioImpl implements DetallePedidoServicio {

    private final DetallePedidoRepositorio detallePedidoRepositorio;

    public DetallePedidoServicioImpl(DetallePedidoRepositorio detallePedidoRepositorio) {
        this.detallePedidoRepositorio = detallePedidoRepositorio;
    }

    @Override
    public void eliminarPorId(Integer detallePedidoId) {
        detallePedidoRepositorio.deleteById(detallePedidoId);
    }
}
