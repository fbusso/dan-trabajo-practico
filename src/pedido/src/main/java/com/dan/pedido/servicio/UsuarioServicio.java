package com.dan.pedido.servicio;

import com.dan.pedido.dto.ClienteDto;
import com.dan.pedido.excepcion.ReglaDeNegociosExcepcion;

import java.math.BigDecimal;

public interface UsuarioServicio {

    BigDecimal obtenerSaldoPorId(Integer id) throws ReglaDeNegociosExcepcion;

    ClienteDto obtenerPorObraId(Integer obraId) throws ReglaDeNegociosExcepcion;
}
