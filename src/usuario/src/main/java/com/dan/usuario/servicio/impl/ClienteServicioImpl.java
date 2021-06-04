package com.dan.usuario.servicio.impl;

import com.dan.usuario.dominio.Cliente;
import com.dan.usuario.dominio.TipoUsuario;
import com.dan.usuario.dominio.Usuario;
import com.dan.usuario.excepcion.ClienteConObrasExcepcion;
import com.dan.usuario.excepcion.ClienteNoEncontradoExcepcion;
import com.dan.usuario.excepcion.ClienteSinObrasExcepcion;
import com.dan.usuario.excepcion.ReglaDeNegociosExcepcion;
import com.dan.usuario.repositorio.ClienteRepositorio;
import com.dan.usuario.servicio.ClienteServicio;
import com.dan.usuario.servicio.UsuarioServicio;
import com.dan.usuario.validador.ClienteValidador;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServicioImpl implements ClienteServicio {

    private final ClienteRepositorio clienteRepositorio;
    private final ClienteValidador clienteValidador;
    private final UsuarioServicio usuarioServicio;

    public ClienteServicioImpl(ClienteRepositorio clienteRepositorio, ClienteValidador clienteValidador, UsuarioServicio usuarioServicio) {
        this.clienteRepositorio = clienteRepositorio;
        this.clienteValidador = clienteValidador;
        this.usuarioServicio = usuarioServicio;
    }

    @Override
    public Cliente crear(Cliente cliente) throws ReglaDeNegociosExcepcion {
        clienteValidador.validadrCreacion(cliente);
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
        return clienteRepositorio.findAllByFechaBajaNotNull();
    }

    @Override
    public Optional<Cliente> obtenerPorId(Integer id) {
        return clienteRepositorio.findById(id);
    }

    @Override
    public Optional<Cliente> buscarPorCuit(String cuit) {
        return clienteRepositorio.findFirstByCuitAndFechaBajaNotNull(cuit);
    }

    @Override
    public List<Cliente> buscarPorRazonSocial(String razonSocial) {
        return clienteRepositorio.findAllByRazonSocialLikeAndFechaBajaNotNull(razonSocial);
    }

    @Override
    public void eliminarPorId(Integer id) throws ReglaDeNegociosExcepcion {
        clienteValidador.validarEliminacion(id);
        clienteRepositorio.deleteById(id);
    }

}
