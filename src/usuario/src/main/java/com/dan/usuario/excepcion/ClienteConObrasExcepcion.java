package com.dan.usuario.excepcion;

public class ClienteConObrasExcepcion extends ReglaDeNegociosExcepcion {

    public ClienteConObrasExcepcion() {
        super("No es posible eliminar un cliente con obras asociadas.");
    }
}
