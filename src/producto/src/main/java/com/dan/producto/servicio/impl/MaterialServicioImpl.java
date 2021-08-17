package com.dan.producto.servicio.impl;

import com.dan.producto.dominio.DetalleProvision;
import com.dan.producto.dominio.Material;
import com.dan.producto.dto.DetallePedidoDto;
import com.dan.producto.dto.PedidoDto;
import com.dan.producto.repositorio.MaterialRepositorio;
import com.dan.producto.servicio.MaterialServicio;
import com.dan.producto.servicio.ProvisionServicio;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MaterialServicioImpl implements MaterialServicio {

    private final MaterialRepositorio materialRepositorio;
    private final ProvisionServicio provisionServicio;

    public MaterialServicioImpl(MaterialRepositorio materialRepositorio, ProvisionServicio provisionServicio) {
        this.materialRepositorio = materialRepositorio;
        this.provisionServicio = provisionServicio;
    }

    @Override
    public Material crear(Material material) {
        return materialRepositorio.save(material);
    }

    @Override
    public List<Material> obtenerSinStockPorId(List<Integer> ids) {
        return materialRepositorio.findAllByIdIsInAndStockActual(ids, 0);
    }

    @Override
    public List<Material> obtenerPorId(List<Integer> ids) {
        return materialRepositorio.findAllByIdIsIn(ids);
    }

    @Override
    public void registrarMovimiento(PedidoDto pedido) {

        Map<Integer, Integer>  mapaMaterialCantidad =  pedido.getDetallePedido()
                .stream()
                .collect(Collectors.toMap(detalle -> detalle.getProducto().getId(), DetallePedidoDto::getCantidad));

        List<DetalleProvision> detalles = new ArrayList<>();
        List<Material> materiales = obtenerPorId(new ArrayList<>(mapaMaterialCantidad.keySet()));
        materiales.forEach(material -> {
            material.setStockActual(material.getStockActual() - mapaMaterialCantidad.get(material.getId()));

            if (material.getStockActual() <= material.getStockMinimo()) {
                detalles.add(new DetalleProvision(material));
            }
        });

        if (!CollectionUtils.isEmpty(detalles)) {
            provisionServicio.crear(detalles);
        }

        materialRepositorio.saveAll(materiales);
    }
}
