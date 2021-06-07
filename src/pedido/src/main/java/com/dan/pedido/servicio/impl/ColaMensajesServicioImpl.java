package com.dan.pedido.servicio.impl;

import com.dan.pedido.configuracion.ConfiguracionRabbitMq;
import com.dan.pedido.excepcion.ColaDeMensajesExcepcion;
import com.dan.pedido.servicio.ColaMensajesServicio;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class ColaMensajesServicioImpl implements ColaMensajesServicio {

    private final RabbitTemplate rabbitTemplate;

    public ColaMensajesServicioImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    // TODO: Parametrizar atributos de la cola de mensajes.
    @Override
    public void enviar(Object mensaje) throws ColaDeMensajesExcepcion {

        try {
            rabbitTemplate.convertAndSend(ConfiguracionRabbitMq.DIRECT_EXCHANGE_NAME, ConfiguracionRabbitMq.ROUTING_KEY, mensaje);
        } catch (Exception excepcion) {
            throw new ColaDeMensajesExcepcion();
        }
    }
}
