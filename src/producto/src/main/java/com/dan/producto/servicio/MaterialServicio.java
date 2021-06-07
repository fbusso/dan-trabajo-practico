package com.dan.producto.servicio;

import com.dan.producto.dominio.Material;
import com.dan.producto.dto.PedidoDto;

import java.util.Collection;
import java.util.List;

public interface MaterialServicio {

    List<Material> obtenerSinStockPorId(List<Integer> ids);

    List<Material> obtenerPorId(List<Integer> ids);

    void registrarMovimiento(PedidoDto pedido);
}
