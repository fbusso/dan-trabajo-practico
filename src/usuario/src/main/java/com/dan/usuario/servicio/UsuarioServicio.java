package com.dan.usuario.servicio;

import com.dan.usuario.dominio.*;
import com.dan.usuario.excepcion.ReglaDeNegociosExcepcion;

import java.util.Optional;

public interface UsuarioServicio {

    Usuario crearUsuario(Registrable registrable) throws ReglaDeNegociosExcepcion;

    Optional<Usuario> obtenerPorNombreUsuario(String mail);
}
