package com.dan.cuenta.controlador;

import com.dan.cuenta.dominio.Pago;
import com.dan.cuenta.servicio.PagoServicio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pago")
public class PagoControlador {

    private final PagoServicio pagoServicio;

    public PagoControlador(PagoServicio pagoServicio) {
        this.pagoServicio = pagoServicio;
    }

    @PostMapping
    public ResponseEntity<Pago> registrar(@RequestBody Pago pago) {
        return new ResponseEntity<>(pagoServicio.registrar(pago), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Pago>> obtenerTodos() {
        return ResponseEntity.ok(pagoServicio.obtenerTodos());
    }
}
