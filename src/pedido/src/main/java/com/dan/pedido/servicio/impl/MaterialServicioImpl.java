package com.dan.pedido.servicio.impl;

import com.dan.pedido.dto.MaterialDto;
import com.dan.pedido.excepcion.ReglaDeNegociosExcepcion;
import com.dan.pedido.servicio.HttpServicio;
import com.dan.pedido.servicio.MaterialServicio;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MaterialServicioImpl implements MaterialServicio {

    private final HttpServicio httpServicio;

    @Value("${materiales.obtener-sin-stock}")
    private static String URL_OBTENER_SIN_STOCK;

    public MaterialServicioImpl(HttpServicio httpServicio) {
        this.httpServicio = httpServicio;
    }

    @Override
    public List<MaterialDto> obtenerSinStockPorId(List<Integer> ids) throws ReglaDeNegociosExcepcion {
        Map<String, String> parametros = new HashMap<>();
        parametros.put("ids", StringUtils.collectionToCommaDelimitedString(ids));
        return Arrays.asList(httpServicio.get(MaterialDto[].class, URL_OBTENER_SIN_STOCK, parametros));
    }
}
