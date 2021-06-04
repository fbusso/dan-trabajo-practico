package com.dan.usuario.validador.impl;

import com.dan.usuario.dominio.Usuario;
import com.dan.usuario.excepcion.ReglaDeNegociosExcepcion;

public interface UsuarioValidador {

    void validarCreacion(Usuario usuario) throws ReglaDeNegociosExcepcion;
}
