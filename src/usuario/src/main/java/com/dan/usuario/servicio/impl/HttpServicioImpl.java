package com.dan.usuario.servicio.impl;

import com.dan.usuario.excepcion.ErrorDeConexionExcepcion;
import com.dan.usuario.excepcion.ReglaDeNegociosExcepcion;
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
    public <T> T get(Class<T> tipo, String url, Object ...args) throws ReglaDeNegociosExcepcion {

        T dto;
        try {
            dto = webClient
                    .get()
                    .uri(url, args)
                    .retrieve()
                    .bodyToMono(tipo)
                    .block();
        } catch (Exception e) {
            throw new ErrorDeConexionExcepcion();
        }

        return dto;
    }
}
