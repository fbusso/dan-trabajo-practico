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
        Usuario usuario = usuarioServicio.crearUsuario(cliente.getUsuario(), TipoUsuario.CLIENTE);
        cliente.setUsuario(usuario);
        return clienteRepositorio.save(cliente);
    }

    @Override
    public Cliente actualizar(Cliente cliente) {
        return clienteRepositorio.save(cliente);
    }

    @Override
    public Cliente obtenerReferencia(Integer id) {
        return clienteRepositorio.getById(id);
    }

    @Override
    public List<Cliente> obtenerTodos() {
        return clienteRepositorio.findAll();
    }

    @Override
    public Optional<Cliente> buscarPorCuit(String cuit) {
        return clienteRepositorio.findFirstByCuit(cuit);
    }

    @Override
    public List<Cliente> buscarPorRazonSocial(String razonSocial) {
        return clienteRepositorio.findAllByRazonSocialLike(razonSocial);
    }

    @Override
    public void eliminarPorId(Integer id) {
        clienteRepositorio.deleteById(id);
    }
}
