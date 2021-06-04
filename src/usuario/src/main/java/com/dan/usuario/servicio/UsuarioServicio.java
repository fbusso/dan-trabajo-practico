package com.dan.usuario.servicio;

import com.dan.usuario.dominio.TipoUsuario;
import com.dan.usuario.dominio.Usuario;
import com.dan.usuario.excepcion.ReglaDeNegociosExcepcion;

import java.util.Optional;

public interface UsuarioServicio {

    Usuario crearUsuario(Usuario usuario, TipoUsuario tipoUsuario) throws ReglaDeNegociosExcepcion;

    Optional<Usuario> obtenerPorNombreUsuario(String mail);
}
