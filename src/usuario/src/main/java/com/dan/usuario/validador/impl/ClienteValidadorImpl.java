package com.dan.usuario.validador.impl;

import com.dan.usuario.dominio.Cliente;
import com.dan.usuario.dto.SituacionCrediticiaDto;
import com.dan.usuario.excepcion.*;
import com.dan.usuario.servicio.BcraServicio;
import com.dan.usuario.servicio.ClienteServicio;
import com.dan.usuario.servicio.PedidoServicio;
import com.dan.usuario.validador.ClienteValidador;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Optional;

@Component
public class ClienteValidadorImpl implements ClienteValidador {

    private final ClienteServicio clienteServicio;
    private final BcraServicio bcraServicio;
    private final PedidoServicio pedidoServicio;

    public ClienteValidadorImpl(@Lazy ClienteServicio clienteServicio, BcraServicio bcraServicio, PedidoServicio pedidoServicio) {
        this.clienteServicio = clienteServicio;
        this.bcraServicio = bcraServicio;
        this.pedidoServicio = pedidoServicio;
    }

    @Override
    public void validadrCreacion(Cliente cliente) throws ReglaDeNegociosExcepcion {

        if (CollectionUtils.isEmpty(cliente.getObras())) {
            throw new ClienteSinObrasExcepcion();
        }

        final SituacionCrediticiaDto situacionCrediticia = bcraServicio.obtenerSituacionCrediticiaPorCuit(cliente.getCuit());

        if (situacionCrediticia == null) {
            throw new SituacionCrediticiaExcepcion();
        }

        if (!SituacionCrediticiaDto.esSituacionValida(situacionCrediticia)) {
            throw new SituacionCrediticiaExcepcion(situacionCrediticia);
        }
    }

    @Override
    public void validarEliminacion(Integer id) throws ReglaDeNegociosExcepcion {
        Optional<Cliente> clienteOptional = clienteServicio.obtenerPorId(id);

        if (clienteOptional.isEmpty() || clienteOptional.get().getFechaBaja() != null) {
            throw new ClienteNoEncontradoExcepcion();
        }

        final Cliente cliente = clienteOptional.get();

        if (!CollectionUtils.isEmpty(pedidoServicio.obtenerPorClienteId(cliente.getId()))) {
            throw new ClienteConPedidosExcepcion();
        }

        if (!CollectionUtils.isEmpty(cliente.getObras())) {
            throw new ClienteConObrasExcepcion();
        }
    }
}
