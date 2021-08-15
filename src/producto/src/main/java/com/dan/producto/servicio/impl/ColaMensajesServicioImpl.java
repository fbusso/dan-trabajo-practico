package com.dan.producto.servicio.impl;

import com.dan.producto.dto.PedidoDto;
import com.dan.producto.servicio.ColaMensajesServicio;
import com.dan.producto.servicio.MaterialServicio;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ColaMensajesServicioImpl implements ColaMensajesServicio {

    private final MaterialServicio materialServicio;

    public ColaMensajesServicioImpl(MaterialServicio materialServicio) {
        this.materialServicio = materialServicio;
    }

    @RabbitListener(queues = "COLA_PEDIDOS")
    public void mensajeRecibido(PedidoDto pedido) {
        materialServicio.registrarMovimiento(pedido);
    }
}
