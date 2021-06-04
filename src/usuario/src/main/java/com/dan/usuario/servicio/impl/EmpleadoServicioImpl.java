package com.dan.usuario.servicio.impl;

import com.dan.usuario.dominio.Empleado;
import com.dan.usuario.dominio.TipoUsuario;
import com.dan.usuario.dominio.Usuario;
import com.dan.usuario.excepcion.ReglaDeNegociosExcepcion;
import com.dan.usuario.repositorio.EmpleadoRepositorio;
import com.dan.usuario.servicio.EmpleadoServicio;
import com.dan.usuario.servicio.UsuarioServicio;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmpleadoServicioImpl implements EmpleadoServicio {

    private final EmpleadoRepositorio empleadoRepositorio;
    private final UsuarioServicio usuarioServicio;

    public EmpleadoServicioImpl(EmpleadoRepositorio empleadoRepositorio, UsuarioServicio usuarioServicio) {
        this.empleadoRepositorio = empleadoRepositorio;
        this.usuarioServicio = usuarioServicio;
    }

    @Override
    public Empleado crear(Empleado empleado) throws ReglaDeNegociosExcepcion {
        Usuario usuario = usuarioServicio.crearUsuario(empleado.getUsuario(), TipoUsuario.CLIENTE);
        empleado.setUsuario(usuario);
        return empleadoRepositorio.save(empleado);
    }

    @Override
    public Empleado actualizar(Empleado empleado) {
        return empleadoRepositorio.save(empleado);
    }

    @Override
    public Optional<Empleado> obtenerPorId(Integer id) {
        return empleadoRepositorio.findById(id);
    }

    @Override
    public void eliminarPorId(Integer id) {
        empleadoRepositorio.deleteById(id);
    }
}
