package com.dan.usuario.excepcion;

/**
 * Excepción genérica de la cual van a extender todas las Excepciones de reglas de negocios.
 */
public abstract class ReglaDeNegociosExcepcion extends Exception {

    public ReglaDeNegociosExcepcion(String mensaje) {
        super(mensaje);
    }
}
