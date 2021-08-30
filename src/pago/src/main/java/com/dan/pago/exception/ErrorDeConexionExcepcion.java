package com.dan.pago.exception;

public class ErrorDeConexionExcepcion extends ReglaDeNegociosExcepcion {

    public ErrorDeConexionExcepcion() {
        super("No es posible comunicarse con el servicio solicitado.");
    }
}
