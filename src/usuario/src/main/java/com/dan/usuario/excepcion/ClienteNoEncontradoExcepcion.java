package com.dan.usuario.excepcion;

public class ClienteNoEncontradoExcepcion extends ReglaDeNegociosExcepcion {

    public ClienteNoEncontradoExcepcion() {
        super("No se encontró el cliente solicitado.");
    }
}
