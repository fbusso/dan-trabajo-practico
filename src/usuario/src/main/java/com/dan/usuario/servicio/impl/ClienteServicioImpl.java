package com.dan.usuario.servicio.impl;

import com.dan.usuario.dominio.Cliente;
import com.dan.usuario.dominio.TipoUsuario;
import com.dan.usuario.dominio.Usuario;
import com.dan.usuario.repositorio.ClienteRepositorio;
import com.dan.usuario.servicio.ClienteServicio;
import com.dan.usuario.servicio.UsuarioServicio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServicioImpl implements ClienteServicio {

    private final ClienteRepositorio clienteRepositorio;
    private final UsuarioServicio usuarioServicio;

    public ClienteServicioImpl(ClienteRepositorio clienteRepositorio, UsuarioServicio usuarioServicio) {
        this.clienteRepositorio = clienteRepositorio;
        this.usuarioServicio = usuarioServicio;
    }

    @Override
    public Cliente crear(Cliente cliente) {
        Usuario usuario = usuarioServicio.crearUsuarioCliente(cliente.getUsuario());
        cliente.setHabilitadoOnline(false);
        cliente.setUsuario(usuario);
        return clienteRepositorio.save(cliente);
    }

    @Override
    public List<Cliente> obtenerTodos() {
        // TODO: Implementar
        return null;
    }

    @Override
    public Optional<Cliente> buscarPorCuit(String cuit) {
        // TODO: Implementar
        return Optional.empty();
    }

    @Override
    public List<Cliente> buscarPorRazonSocial(String razonSocial) {
        // TODO: Implementar
        return null;
    }
}
