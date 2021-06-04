package com.dan.usuario.validador.impl;

import com.dan.usuario.dominio.Cliente;
import com.dan.usuario.dto.SituacionCrediticiaDto;
import com.dan.usuario.excepcion.*;
import com.dan.usuario.validador.ClienteValidador;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Component
public class ClienteValidadorImpl implements ClienteValidador {

    private final WebClient webClient;

    public ClienteValidadorImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public void validadrCreacion(Cliente cliente) throws ReglaDeNegociosExcepcion {

        if (CollectionUtils.isEmpty(cliente.getObras())) {
            throw new ClienteSinObrasExcepcion();
        }

        SituacionCrediticiaDto situacionCrediticia;
        try {
            final String URL = "http://localhost:9010/api/sitaucion-crediticia/{cuit}";
            situacionCrediticia = webClient
                    .get()
                    .uri(URL, cliente.getCuit())
                    .retrieve()
                    .bodyToMono(SituacionCrediticiaDto.class)
                    .block();
        } catch (Exception e) {
            throw new SituacionCrediticiaExcepcion();
        }

        if (situacionCrediticia == null) {
            throw new SituacionCrediticiaExcepcion();
        }

        if (!SituacionCrediticiaDto.NORMAL.equals(situacionCrediticia) && !SituacionCrediticiaDto.BAJO.equals(situacionCrediticia)) {
            throw new SituacionCrediticiaExcepcion(situacionCrediticia);
        }
    }

    @Override
    public void validarEliminacion(Optional<Cliente> clienteOptional) throws ReglaDeNegociosExcepcion {
        if (clienteOptional.isEmpty() || clienteOptional.get().getFechaBaja() != null) {
            throw new ClienteNoEncontradoExcepcion();
        }

        final Cliente cliente = clienteOptional.get();

        if (CollectionUtils.isEmpty(cliente.getObras())) {
            throw new ClienteConObrasExcepcion();
        }
    }
}
