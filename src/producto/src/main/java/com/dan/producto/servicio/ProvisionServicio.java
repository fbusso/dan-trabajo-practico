package com.dan.producto.servicio;

import com.dan.producto.dominio.DetalleProvision;

import java.util.List;

public interface ProvisionServicio {

    void crear(List<DetalleProvision> detalle);
}
