package com.dan.usuario.validador;

import com.dan.usuario.dominio.Cliente;
import com.dan.usuario.excepcion.ReglaDeNegociosExcepcion;

public interface ClienteValidador {

    void validadrCreacion(Cliente cliente) throws ReglaDeNegociosExcepcion;

    void validarEliminacion(Integer id) throws ReglaDeNegociosExcepcion;
}
