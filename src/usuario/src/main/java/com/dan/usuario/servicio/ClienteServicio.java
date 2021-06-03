package com.dan.usuario.servicio;

import com.dan.usuario.dominio.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteServicio {

    Cliente crear(Cliente cliente);

    List<Cliente> obtenerTodos();

    Optional<Cliente> buscarPorCuit(String cuit);

    List<Cliente> buscarPorRazonSocial(String razonSocial);

}
