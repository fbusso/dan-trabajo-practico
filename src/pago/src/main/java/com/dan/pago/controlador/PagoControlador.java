package com.dan.pago.controlador;

import com.dan.pago.dto.PedidoDto;
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
    public ResponseEntity<String> generarLinkPago(@RequestBody PedidoDto pedidoDto) {
        try {
            return new ResponseEntity<>(mercadoPagoServicio.generarPreferencia(pedidoDto), HttpStatus.CREATED);
        } catch (MPException e) {
            return new ResponseEntity<>("ERROR", HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
