package com.dan.usuario.servicio.impl;

import com.dan.usuario.excepcion.ErrorDeConexionExcepcion;
import com.dan.usuario.excepcion.ReglaDeNegociosExcepcion;
import com.dan.usuario.servicio.HttpServicio;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class HttpServicioImpl implements HttpServicio {

    private static final String RESILIENCE4J_INSTANCE_NAME = "circuit_breaker";
    private static final String FALLBACK_METHOD = "respuestaPorDefecto";

    private final WebClient webClient;

    public HttpServicioImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    @CircuitBreaker(name = RESILIENCE4J_INSTANCE_NAME, fallbackMethod = FALLBACK_METHOD)
    public <T> T get(Class<T> tipo , String url, Object ...args) throws ReglaDeNegociosExcepcion {

        T dto;
        try {
            dto = webClient
                    .get()
                    .uri(url, args)
                    .retrieve()
                    .bodyToMono(tipo)
                    .block();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ErrorDeConexionExcepcion();
        }

        return dto;
    }

    public void respuestaPorDefecto(Exception e) throws ReglaDeNegociosExcepcion {
        throw new ErrorDeConexionExcepcion();
    }
}
