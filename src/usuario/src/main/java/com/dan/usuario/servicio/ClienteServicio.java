package com.dan.usuario.servicio;

import com.dan.usuario.dominio.Cliente;
import com.dan.usuario.excepcion.ClienteNoEncontradoExcepcion;
import com.dan.usuario.excepcion.ReglaDeNegociosExcepcion;

import javax.persistence.criteria.CriteriaBuilder;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ClienteServicio {

    Cliente crear(Cliente cliente) throws ReglaDeNegociosExcepcion;

    Cliente actualizar(Cliente cliente);

    Cliente obtenerReferencia(Integer id);

    List<Cliente> obtenerTodos();

    Optional<Cliente> obtenerPorId(Integer id);

    Optional<Cliente> obtenerPorObraId(Integer obraId);

    Optional<Cliente> buscarPorCuit(String cuit);

    List<Cliente> buscarPorRazonSocial(String razonSocial);

    BigDecimal obtenerSaldoPorId(Integer id) throws ClienteNoEncontradoExcepcion;

    void eliminarPorId(Integer id) throws ReglaDeNegociosExcepcion;

}
