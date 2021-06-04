package com.dan.usuario.validador.impl;

import com.dan.usuario.dominio.Usuario;
import com.dan.usuario.excepcion.ReglaDeNegociosExcepcion;
import com.dan.usuario.excepcion.UsuarioExistenteExcepcion;
import com.dan.usuario.servicio.UsuarioServicio;
import com.dan.usuario.validador.UsuarioValidador;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class UsuarioValidadorImpl implements UsuarioValidador {

    private final UsuarioServicio usuarioServicio;

    public UsuarioValidadorImpl(@Lazy UsuarioServicio usuarioServicio) {
        this.usuarioServicio = usuarioServicio;
    }

    @Override
    public void validarCreacion(Usuario usuario) throws ReglaDeNegociosExcepcion {

        if (usuarioServicio.obtenerPorNombreUsuario(usuario.getNombreUsuario()).isPresent()) {
            throw new UsuarioExistenteExcepcion();
        }

    }
}
