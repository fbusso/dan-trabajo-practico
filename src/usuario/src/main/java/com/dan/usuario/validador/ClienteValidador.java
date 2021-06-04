package com.dan.usuario.validador;

import com.dan.usuario.dominio.Cliente;
import com.dan.usuario.excepcion.ClienteConObrasExcepcion;
import com.dan.usuario.excepcion.ClienteNoEncontradoExcepcion;
import com.dan.usuario.excepcion.ClienteSinObrasExcepcion;
import com.dan.usuario.excepcion.ReglaDeNegociosExcepcion;

import java.util.Optional;

public interface ClienteValidador {

    void validadrCreacion(Cliente cliente) throws ReglaDeNegociosExcepcion;

    void validarEliminacion(Optional<Cliente> clienteOptional) throws ReglaDeNegociosExcepcion;
}
