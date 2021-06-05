package com.dan.usuario.servicio;

import com.dan.usuario.dto.PedidoDto;
import com.dan.usuario.excepcion.ReglaDeNegociosExcepcion;

import java.util.List;

public interface PedidoServicio {

    List<PedidoDto> obtenerPorClienteId(Integer id) throws ReglaDeNegociosExcepcion;
}
