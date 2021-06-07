package com.dan.pedido.excepcion.handler;

import com.dan.pedido.excepcion.ReglaDeNegociosExcepcion;

public class PedidoInexistenteExcepcion extends ReglaDeNegociosExcepcion {

    public PedidoInexistenteExcepcion() {
        super("No existe el pedido solicitado.");
    }
}
