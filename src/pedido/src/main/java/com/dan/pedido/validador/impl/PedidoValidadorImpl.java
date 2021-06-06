package com.dan.pedido.validador.impl;

import com.dan.pedido.dominio.Pedido;
import com.dan.pedido.dominio.Producto;
import com.dan.pedido.excepcion.PedidoSinDetallesExcepcion;
import com.dan.pedido.excepcion.ReglaDeNegociosExcepcion;
import com.dan.pedido.excepcion.handler.PedidoInexistenteExcepcion;
import com.dan.pedido.excepcion.handler.ProductoInvalidoExcepcion;
import com.dan.pedido.servicio.PedidoServicio;
import com.dan.pedido.validador.PedidoValidador;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Optional;

@Component
public class PedidoValidadorImpl implements PedidoValidador {

    private final PedidoServicio pedidoServicio;

    public PedidoValidadorImpl(@Lazy PedidoServicio pedidoServicio) {
        this.pedidoServicio = pedidoServicio;
    }

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

    @Override
    public Pedido validarConfirmacion(Integer id) throws ReglaDeNegociosExcepcion {

        Optional<Pedido> pedidoOptional = pedidoServicio.obtenerPorId(id);
        if (pedidoOptional.isEmpty()) {
            throw new PedidoInexistenteExcepcion();
        }

        final Pedido pedido = pedidoOptional.get();



        return pedidoOptional.get();

    }
}
