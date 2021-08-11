package com.dan.usuario.servicio.impl;

import com.dan.usuario.dominio.Cliente;
import com.dan.usuario.dominio.Obra;
import com.dan.usuario.dominio.TipoObra;
import com.dan.usuario.repositorio.ObraRepositorio;
import com.dan.usuario.servicio.ClienteServicio;
import com.dan.usuario.servicio.ObraServicio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ObraServicioImpl implements ObraServicio {

    private final ObraRepositorio obraRepositorio;
    private final ClienteServicio clienteServicio;

    public ObraServicioImpl(ObraRepositorio obraRepositorio, ClienteServicio clienteServicio) {
        this.obraRepositorio = obraRepositorio;
        this.clienteServicio = clienteServicio;
    }

    @Override
    public Obra crear(Obra obra, Integer clienteId) {
        Cliente cliente = clienteServicio.obtenerReferencia(clienteId);
        obra.setCliente(cliente);
        return obraRepositorio.save(obra);
    }

    @Override
    public Optional<Obra> obtenerPorId(Integer id) {
        return obraRepositorio.findById(id);
    }

    @Override
    public List<Obra> obtenerTodos() {
        return obraRepositorio.findAll();
    }

    @Override
    public List<Obra> obtenerPorClienteIdOTipoObra(Integer clienteId, TipoObra tipoObra) {
        return obraRepositorio.findAllByCliente_IdOrTipoObra(clienteId, tipoObra);
    }

    @Override
    public void eliminarPorId(Integer id) {
        obraRepositorio.deleteById(id);
    }
}
