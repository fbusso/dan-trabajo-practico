package com.dan.usuario.validador.impl;

import com.dan.usuario.dominio.Cliente;
import com.dan.usuario.dto.SituacionCrediticiaDto;
import com.dan.usuario.excepcion.*;
import com.dan.usuario.servicio.BcraServicio;
import com.dan.usuario.servicio.ClienteServicio;
import com.dan.usuario.validador.ClienteValidador;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Optional;

@Component
public class ClienteValidadorImpl implements ClienteValidador {

    private final ClienteServicio clienteServicio;
    private final BcraServicio bcraServicio;

    public ClienteValidadorImpl(@Lazy ClienteServicio clienteServicio, BcraServicio bcraServicio) {
        this.clienteServicio = clienteServicio;
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
    public void validarEliminacion(Integer id) throws ReglaDeNegociosExcepcion {
        Optional<Cliente> clienteOptional = clienteServicio.obtenerPorId(id);

        if (clienteOptional.isEmpty() || clienteOptional.get().getFechaBaja() != null) {
            throw new ClienteNoEncontradoExcepcion();
        }

        if (CollectionUtils.isEmpty(clienteOptional.get().getObras())) {
            throw new ClienteConObrasExcepcion();
        }
    }
}
