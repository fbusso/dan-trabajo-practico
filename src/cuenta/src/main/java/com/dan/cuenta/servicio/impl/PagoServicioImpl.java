package com.dan.cuenta.servicio.impl;

import com.dan.cuenta.dominio.Pago;
import com.dan.cuenta.repositorio.PagoRepositorio;
import com.dan.cuenta.servicio.PagoServicio;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PagoServicioImpl implements PagoServicio {

    private final PagoRepositorio pagoRepositorio;

    public PagoServicioImpl(PagoRepositorio pagoRepositorio) {
        this.pagoRepositorio = pagoRepositorio;
    }

    public Pago registrar(Pago pago) {
        pago.setFechaPago(LocalDate.now());
        return pagoRepositorio.save(pago);
    }

    public List<Pago> obtenerTodos() {
        return pagoRepositorio.findAll();
    }
}
