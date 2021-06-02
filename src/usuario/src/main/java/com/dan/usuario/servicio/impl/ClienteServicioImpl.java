package com.dan.usuario.servicio.impl;

import com.dan.usuario.dominio.Cliente;
import com.dan.usuario.servicio.ClienteServicio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServicioImpl implements ClienteServicio {

    @Override
    public Cliente crear(Cliente cliente) {
        // TODO: Implementar
        return null;
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
