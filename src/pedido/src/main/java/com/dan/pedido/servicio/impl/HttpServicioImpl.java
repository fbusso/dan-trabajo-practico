package com.dan.pedido.servicio.impl;

import com.dan.pedido.excepcion.ErrorDeConexionExcepcion;
import com.dan.pedido.excepcion.ReglaDeNegociosExcepcion;
import com.dan.pedido.servicio.HttpServicio;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
public class HttpServicioImpl implements HttpServicio {

    private final WebClient webClient;

    public HttpServicioImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    // TODO: Manejar flujo de errores
    @Override
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

    @Override
    public <T> T get(Class<T> tipo , String url, Map<String, ?> parametros) throws ReglaDeNegociosExcepcion {

        T dto;
        try {
            dto = webClient
                    .get()
                    .uri(url, parametros)
                    .retrieve()
                    .bodyToMono(tipo)
                    .block();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ErrorDeConexionExcepcion();
        }

        return dto;
    }

}
