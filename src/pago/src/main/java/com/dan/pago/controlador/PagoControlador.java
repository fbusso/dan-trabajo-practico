package com.dan.pago.controlador;

import com.dan.pago.dominio.Carrito;
import com.dan.pago.servicio.MercadoPagoServicio;
import com.mercadopago.exceptions.MPException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/pago")
public class PagoControlador {
    public final MercadoPagoServicio mercadoPagoServicio;

    public PagoControlador(MercadoPagoServicio mercadoPagoServicio) {
        this.mercadoPagoServicio = mercadoPagoServicio;
    }

    @PostMapping
    public ResponseEntity<String> generarLinkPago(@RequestBody Carrito carrito) {
        try {
            return new ResponseEntity<>(mercadoPagoServicio.generarLinkPago(carrito), HttpStatus.CREATED);
        } catch (MPException e) {
            return new ResponseEntity<>("ERROR", HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
