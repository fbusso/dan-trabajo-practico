package com.dan.usuario.servicio.impl;

import com.dan.usuario.dto.PedidoDto;
import com.dan.usuario.excepcion.ReglaDeNegociosExcepcion;
import com.dan.usuario.servicio.HttpServicio;
import com.dan.usuario.servicio.PedidoServicio;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class PedidoServicioImpl implements PedidoServicio {

    private final HttpServicio httpServicio;

    @Value("${pedidos.obtener-pedidos-por-cliente-id}")
    private String URL;

    public PedidoServicioImpl(HttpServicio httpServicio) {
        this.httpServicio = httpServicio;
    }

    @Override
    public List<PedidoDto> obtenerPorClienteId(Integer id) throws ReglaDeNegociosExcepcion {
        return Arrays.asList(httpServicio.get(PedidoDto[].class, URL, id));
    }
}
