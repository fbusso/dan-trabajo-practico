package com.dan.usuario.servicio.impl;

import com.dan.usuario.servicio.HttpServicio;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class HttpServicioImpl implements HttpServicio {

    private final WebClient webClient;

    public HttpServicioImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    // TODO: Manejar flujo de errores
    @Override
    public <T> T get(Class<T> cast, String url, Object ...args) {

        T dto = null;
        try {
            dto = webClient
                    .get()
                    .uri(url, args)
                    .retrieve()
                    .bodyToMono(cast)
                    .block();
        } catch (Exception e) {
//            throw new SituacionCrediticiaExcepcion();
        }

        return dto;
    }
}
