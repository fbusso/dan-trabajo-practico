package com.dan.usuario.validador.impl;

import com.dan.usuario.dominio.Cliente;
import com.dan.usuario.dto.SituacionCrediticiaDto;
import com.dan.usuario.excepcion.*;
import com.dan.usuario.servicio.BcraServicio;
import com.dan.usuario.validador.ClienteValidador;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Component
public class ClienteValidadorImpl implements ClienteValidador {

    private final WebClient webClient;
    private final BcraServicio bcraServicio;

    public ClienteValidadorImpl(WebClient webClient, BcraServicio bcraServicio) {
        this.webClient = webClient;
        this.bcraServicio = bcraServicio;
    }

    @Override
    public void validadrCreacion(Cliente cliente) throws ReglaDeNegociosExcepcion {

        if (CollectionUtils.isEmpty(cliente.getObras())) {
            throw new ClienteSinObrasExcepcion();
        }

        SituacionCrediticiaDto situacionCrediticia = bcraServicio.obtenerSituacionCrediticiaPorCuit(cliente.getCuit());

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
