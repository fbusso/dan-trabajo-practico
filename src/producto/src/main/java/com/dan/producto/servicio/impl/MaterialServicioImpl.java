package com.dan.producto.servicio.impl;

import com.dan.producto.dominio.Material;
import com.dan.producto.repositorio.MaterialRepositorio;
import com.dan.producto.servicio.MaterialServicio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialServicioImpl implements MaterialServicio {

    private final MaterialRepositorio materialRepositorio;

    public MaterialServicioImpl(MaterialRepositorio materialRepositorio) {
        this.materialRepositorio = materialRepositorio;
    }

    @Override
    public List<Material> obtenerSinStockPorId(List<Integer> ids) {
        return materialRepositorio.findAllByIdIsInAndStockActual(ids, 0);
    }
}
