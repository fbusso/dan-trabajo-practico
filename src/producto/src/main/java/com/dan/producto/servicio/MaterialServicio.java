package com.dan.producto.servicio;

import com.dan.producto.dominio.Material;

import java.util.List;

public interface MaterialServicio {

    List<Material> obtenerSinStockPorId(List<Integer> ids);
}
