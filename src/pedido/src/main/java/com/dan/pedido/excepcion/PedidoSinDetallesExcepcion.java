package com.dan.pedido.excepcion;

public class PedidoSinDetallesExcepcion extends ReglaDeNegociosExcepcion {

    public PedidoSinDetallesExcepcion() {
        super("No es posible crear un Pedido sin Detalles asociados.");
    }
}
