package com.dan.producto.servicio;

import com.dan.producto.dominio.Material;
import com.dan.producto.dto.PedidoDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MaterialServicio {

    Material crear(Material material);

    List<Material> obtenerSinStockPorId(List<Integer> ids);

    List<Material> obtenerPorId(List<Integer> ids);

    Page<Material> obtenerTodos(Integer pagina, Integer cantidadRegistros);

    void registrarMovimiento(PedidoDto pedido);
}
