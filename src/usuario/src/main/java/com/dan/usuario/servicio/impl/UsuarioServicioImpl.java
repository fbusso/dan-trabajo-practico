package com.dan.usuario.servicio.impl;

import com.dan.usuario.dominio.*;
import com.dan.usuario.excepcion.ReglaDeNegociosExcepcion;
import com.dan.usuario.repositorio.UsuarioRepositorio;
import com.dan.usuario.servicio.UsuarioServicio;
import com.dan.usuario.validador.UsuarioValidador;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServicioImpl implements UsuarioServicio {

    private final UsuarioRepositorio usuarioRepositorio;
    private final UsuarioValidador usuarioValidador;

    public UsuarioServicioImpl(UsuarioRepositorio usuarioRepositorio, UsuarioValidador usuarioValidador) {
        this.usuarioRepositorio = usuarioRepositorio;
        this.usuarioValidador = usuarioValidador;
    }

    @Override
    public Usuario crearUsuario(Registrable registrable) throws ReglaDeNegociosExcepcion {
        Usuario usuario = new Usuario(registrable);
        usuarioValidador.validarCreacion(usuario);
        return usuarioRepositorio.save(usuario);
    }

    @Override
    public Optional<Usuario> obtenerPorNombreUsuario(String mail) {
        return usuarioRepositorio.findTopByNombreUsuario(mail);
    }
}
