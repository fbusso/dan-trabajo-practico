package com.dan.usuario.configuracion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.Builder;

@Configuration
public class WebClientConfiguracion {

    @Bean
    WebClient webClient(Builder webClientBuilder) {
        return webClientBuilder.build();
    }
}
