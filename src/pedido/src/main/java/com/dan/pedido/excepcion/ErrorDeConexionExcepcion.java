package com.dan.pedido.excepcion;

public class ErrorDeConexionExcepcion extends ReglaDeNegociosExcepcion {

    public ErrorDeConexionExcepcion() {
        super("No es posible comunicarse con el servicio solicitado.");
    }
}
