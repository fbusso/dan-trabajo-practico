package com.dan.usuario.excepcion;

public class ErrorDeConexionExcepcion extends ReglaDeNegociosExcepcion {

    public ErrorDeConexionExcepcion() {
        super("No es posible comunicarse con el servicio solicitado.");
    }
}
