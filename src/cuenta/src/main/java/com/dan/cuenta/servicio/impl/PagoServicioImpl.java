package com.dan.cuenta.servicio.impl;

import com.dan.cuenta.dominio.Transferencia;
import com.dan.cuenta.dominio.Pago;
import com.dan.cuenta.repositorio.PagoRepositorio;
import com.dan.cuenta.servicio.PagoServicio;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PagoServicioImpl implements PagoServicio {

    private final PagoRepositorio pagoRepositorio;

    public PagoServicioImpl(PagoRepositorio pagoRepositorio) {
        this.pagoRepositorio = pagoRepositorio;
    }

    public Pago registrar(Pago pago) {


        if (pago.getMedioPago() instanceof Transferencia) {
            Transferencia transferencia = (Transferencia) pago.getMedioPago();
            transferencia.getCbuDestino();
        }


        pago.setFechaPago(LocalDate.now());
        return pagoRepositorio.save(pago);
    }
}
