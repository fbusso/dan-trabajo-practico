package com.dan.pedido.configuracion;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ConfiguracionRabbitMq {

    public static final String DIRECT_EXCHANGE_NAME = "COLA_PEDIDOS-EXCHANGE";
    public static final String NOMBRE_COLA = "COLA_PEDIDOS";
    public static final String ROUTING_KEY = "COLA_PEDIDOS-ROUTING-KEY";

    @Bean
    Queue queue() {
        return new Queue(NOMBRE_COLA);
    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange(DIRECT_EXCHANGE_NAME);
    }

    @Bean
    Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
