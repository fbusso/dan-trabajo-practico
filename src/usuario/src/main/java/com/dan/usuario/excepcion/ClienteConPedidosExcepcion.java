package com.dan.usuario.excepcion;

public class ClienteConPedidosExcepcion extends ReglaDeNegociosExcepcion {

    public ClienteConPedidosExcepcion() {
        super("No es posible eliminar un cliente con pedidos asociados.");
    }
}
