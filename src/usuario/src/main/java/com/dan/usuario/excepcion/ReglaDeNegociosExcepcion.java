package com.dan.usuario.excepcion;

public abstract class ReglaDeNegociosExcepcion extends Exception {

    public ReglaDeNegociosExcepcion(String mensaje) {
        super(mensaje);
    }
}
