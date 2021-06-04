package com.dan.usuario.excepcion;

public class UsuarioExistenteExcepcion extends ReglaDeNegociosExcepcion {

    public UsuarioExistenteExcepcion() {
        super("Ya existe un usuario con el Nombre de Usuario ingresado.");
    }
}
