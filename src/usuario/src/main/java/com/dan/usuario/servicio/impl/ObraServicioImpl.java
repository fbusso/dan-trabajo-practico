package com.dan.usuario.servicio.impl;

import com.dan.usuario.dominio.Cliente;
import com.dan.usuario.dominio.Obra;
import com.dan.usuario.repositorio.ObraRepositorio;
import com.dan.usuario.servicio.ClienteServicio;
import com.dan.usuario.servicio.ObraServicio;
import org.springframework.stereotype.Service;

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
    public Optional<Obra> obtenerPorId(Integer obraId) {
        return obraRepositorio.findById(obraId);
    }
}
