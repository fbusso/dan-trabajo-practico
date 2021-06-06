package com.dan.pedido.servicio.impl;

import com.dan.pedido.configuracion.ConfiguracionRabbitMq;
import com.dan.pedido.servicio.ColaMensajesServicio;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class ColaMensajesServicioImpl implements ColaMensajesServicio {

    private final RabbitTemplate rabbitTemplate;

    public ColaMensajesServicioImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void enviar(Object mensaje) {
        // TODO: Parametrizar atributos de la cola de mensajes.
        rabbitTemplate.convertAndSend(ConfiguracionRabbitMq.DIRECT_EXCHANGE_NAME, ConfiguracionRabbitMq.ROUTING_KEY, mensaje);
    }
}
