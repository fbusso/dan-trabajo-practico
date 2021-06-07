package com.dan.pedido.validador;

import com.dan.pedido.dominio.Pedido;
import com.dan.pedido.excepcion.ReglaDeNegociosExcepcion;

public interface PedidoValidador {

    void validarCreacion(Pedido pedido) throws ReglaDeNegociosExcepcion;

    Pedido validarConfirmacion(Integer id) throws ReglaDeNegociosExcepcion;
}
