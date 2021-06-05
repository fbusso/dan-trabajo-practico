package com.dan.usuario.servicio.impl;

import com.dan.usuario.dto.PedidoDto;
import com.dan.usuario.excepcion.ReglaDeNegociosExcepcion;
import com.dan.usuario.servicio.HttpServicio;
import com.dan.usuario.servicio.PedidoServicio;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class PedidoServicioImpl implements PedidoServicio {

    private final HttpServicio httpServicio;

    public PedidoServicioImpl(HttpServicio httpServicio) {
        this.httpServicio = httpServicio;
    }

    @Override
    public List<PedidoDto> obtenerPorClienteId(Integer id) throws ReglaDeNegociosExcepcion {
        // TODO: Parametrización de URLs
        final String URL = "http://localhost:9002/api/pedido/cliente/{id}";
        return Arrays.asList(httpServicio.get(PedidoDto[].class, URL, id));
    }
}
