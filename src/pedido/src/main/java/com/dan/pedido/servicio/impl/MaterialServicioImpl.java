package com.dan.pedido.servicio.impl;

import com.dan.pedido.dto.MaterialDto;
import com.dan.pedido.excepcion.ReglaDeNegociosExcepcion;
import com.dan.pedido.servicio.HttpServicio;
import com.dan.pedido.servicio.MaterialServicio;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MaterialServicioImpl implements MaterialServicio {

    private final HttpServicio httpServicio;

    public MaterialServicioImpl(HttpServicio httpServicio) {
        this.httpServicio = httpServicio;
    }

    @Override
    public List<MaterialDto> obtenerSinStockPorId(List<Integer> ids) throws ReglaDeNegociosExcepcion {
        // TODO: Parametrizar URLs
        final String URL = "http://localhost:9001/api/material/sin-stock";
        Map<String, List<Integer>> parametros = new HashMap<>();
        parametros.put("ids", ids);
        return Arrays.asList(httpServicio.get(MaterialDto[].class, URL, parametros));
    }
}
