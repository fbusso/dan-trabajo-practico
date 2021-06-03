package com.dan.usuario.servicio.impl;

import com.dan.usuario.dominio.TipoUsuario;
import com.dan.usuario.dominio.Usuario;
import com.dan.usuario.repositorio.UsuarioRepositorio;
import com.dan.usuario.servicio.UsuarioServicio;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServicioImpl implements UsuarioServicio {

    private final UsuarioRepositorio usuarioRepositorio;

    public UsuarioServicioImpl(UsuarioRepositorio usuarioRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
    }

    @Override
    public Usuario crearUsuario(Usuario usuario, TipoUsuario tipoUsuario) {
        usuario.setTipoUsuario(tipoUsuario);
        return usuarioRepositorio.save(usuario);
    }

}
