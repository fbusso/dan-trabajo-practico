package com.dan.producto.servicio.impl;

import com.dan.producto.dominio.DetalleProvision;
import com.dan.producto.dominio.Provision;
import com.dan.producto.repositorio.ProvisionRepositorio;
import com.dan.producto.servicio.ProvisionServicio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvisionServicioImpl implements ProvisionServicio {

    private final ProvisionRepositorio provisionRepositorio;

    public ProvisionServicioImpl(ProvisionRepositorio provisionRepositorio) {
        this.provisionRepositorio = provisionRepositorio;
    }

    @Override
    public void crear(List<DetalleProvision> detalle) {
        final Provision provision = new Provision(detalle);
        provisionRepositorio.save(provision);
    }
}
