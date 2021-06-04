package com.dan.usuario.servicio.impl;

import com.dan.usuario.dto.SituacionCrediticiaDto;
import com.dan.usuario.excepcion.ReglaDeNegociosExcepcion;
import com.dan.usuario.servicio.BcraServicio;
import com.dan.usuario.servicio.HttpServicio;
import org.springframework.stereotype.Service;

@Service
public class BcraServicioImpl implements BcraServicio {

    private final HttpServicio httpServicio;

    public BcraServicioImpl(HttpServicio httpServicio) {
        this.httpServicio = httpServicio;
    }

    @Override
    public SituacionCrediticiaDto obtenerSituacionCrediticiaPorCuit(String cuit) throws ReglaDeNegociosExcepcion {
        final String URL = "http://localhost:9010/api/sitaucion-crediticia/{cuit}";
        return httpServicio.get(SituacionCrediticiaDto.class, URL, cuit);
    }
}
