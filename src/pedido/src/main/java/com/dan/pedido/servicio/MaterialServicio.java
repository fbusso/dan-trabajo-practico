package com.dan.pedido.servicio;

import com.dan.pedido.dto.MaterialDto;
import com.dan.pedido.excepcion.ReglaDeNegociosExcepcion;

import java.util.List;

public interface MaterialServicio {

    List<MaterialDto> obtenerSinStockPorId(List<Integer> ids) throws ReglaDeNegociosExcepcion;
}
