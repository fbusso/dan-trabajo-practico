package com.dan.usuario.servicio;

import com.dan.usuario.dominio.TipoUsuario;
import com.dan.usuario.dominio.Usuario;

public interface UsuarioServicio {

    Usuario crearUsuario(Usuario usuario, TipoUsuario tipoUsuario);
}
