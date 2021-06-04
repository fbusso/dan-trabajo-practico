package com.dan.pedido.excepcion.handler;

import com.dan.pedido.excepcion.ReglaDeNegociosExcepcion;

public class ProductoInvalidoExcepcion extends ReglaDeNegociosExcepcion {

    public ProductoInvalidoExcepcion() {
        super("Todos los Productos del Detalle deben tener un Nombre y un Precio.");
    }
}
