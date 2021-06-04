package com.dan.usuario.excepcion;

public class ClienteSinObrasExcepcion extends ReglaDeNegociosExcepcion {

    public ClienteSinObrasExcepcion() {
        super("No es posible crear un cliente sin obras.");
    }
}
