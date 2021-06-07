package com.dan.cuenta.servicio;

import com.dan.cuenta.dominio.Pago;

import java.util.List;

public interface PagoServicio {

    Pago registrar(Pago pago);

    List<Pago> obtenerTodos();
}
