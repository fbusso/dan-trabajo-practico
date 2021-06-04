package com.dan.pedido.validador.impl;

import com.dan.pedido.dominio.Pedido;
import com.dan.pedido.dominio.Producto;
import com.dan.pedido.excepcion.PedidoSinDetallesExcepcion;
import com.dan.pedido.excepcion.ReglaDeNegociosExcepcion;
import com.dan.pedido.excepcion.handler.ProductoInvalidoExcepcion;
import com.dan.pedido.validador.PedidoValidador;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
public class PedidoValidadorImpl implements PedidoValidador {

    public void validarCreacion(Pedido pedido) throws ReglaDeNegociosExcepcion {

        if (CollectionUtils.isEmpty(pedido.getDetallePedido())) {
            throw new PedidoSinDetallesExcepcion();
        }

        if (pedido.getDetallePedido().stream().anyMatch(detallePedido -> !productoValido(detallePedido.getProducto()))) {
            throw new ProductoInvalidoExcepcion();
        }

    }

    private Boolean productoValido(Producto producto) {
        return producto != null
                && producto.getPrecio() != null
                && StringUtils.isNotBlank(producto.getDescripcion());

    }

}
