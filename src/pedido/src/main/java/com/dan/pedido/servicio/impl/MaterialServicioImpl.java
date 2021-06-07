package com.dan.pedido.servicio.impl;

import com.dan.pedido.dto.MaterialDto;
import com.dan.pedido.excepcion.ReglaDeNegociosExcepcion;
import com.dan.pedido.servicio.HttpServicio;
import com.dan.pedido.servicio.MaterialServicio;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        Map<String, String> parametros = new HashMap<>();
        parametros.put("ids", StringUtils.collectionToCommaDelimitedString(ids));
        return Arrays.asList(httpServicio.get(MaterialDto[].class, URL, parametros));
    }
}
