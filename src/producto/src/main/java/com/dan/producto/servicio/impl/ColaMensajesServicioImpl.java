package com.dan.producto.servicio.impl;

import com.dan.producto.servicio.ColaMensajesServicio;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ColaMensajesServicioImpl implements ColaMensajesServicio {


    @RabbitListener(queues = "COLA_PEDIDOS")
    // TODO: Implementación. Test
    public void mensajeRecibido(Object pedido) {
        System.out.println("Mensaje recibido desde RabbitMQ: " + pedido);
    }
}
