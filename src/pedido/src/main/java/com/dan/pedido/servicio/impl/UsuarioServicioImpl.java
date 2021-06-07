package com.dan.pedido.servicio.impl;

import com.dan.pedido.dto.ClienteDto;
import com.dan.pedido.excepcion.ReglaDeNegociosExcepcion;
import com.dan.pedido.servicio.HttpServicio;
import com.dan.pedido.servicio.UsuarioServicio;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UsuarioServicioImpl implements UsuarioServicio {

    private final HttpServicio httpServicio;

    public UsuarioServicioImpl(HttpServicio httpServicio) {
        this.httpServicio = httpServicio;
    }

    @Override
    public BigDecimal obtenerSaldoPorId(Integer id) throws ReglaDeNegociosExcepcion {
        // TODO: Parametrizar URLs
        final String URL = "http://localhost:9000/api/cliente/id/{id}/saldo";
        return httpServicio.get(BigDecimal.class, URL, id);
    }

    @Override
    public ClienteDto obtenerPorObraId(Integer obraId) throws ReglaDeNegociosExcepcion {
        // TODO: Parametrizar URLs
        final String URL = "http://localhost:9000/api/cliente/obra/id/{id}";
        return httpServicio.get(ClienteDto.class, URL, obraId);
    }
}
