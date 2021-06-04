package com.dan.usuario.validador;

import com.dan.usuario.dominio.Usuario;
import com.dan.usuario.excepcion.ReglaDeNegociosExcepcion;

public interface UsuarioValidador {

    void validarCreacion(Usuario usuario) throws ReglaDeNegociosExcepcion;
}
