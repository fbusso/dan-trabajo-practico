package com.dan.producto.controlador;

import com.dan.producto.dominio.Material;
import com.dan.producto.servicio.MaterialServicio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/material")
public class MaterialControlador {

    private final MaterialServicio materialServicio;

    public MaterialControlador(MaterialServicio materialServicio) {
        this.materialServicio = materialServicio;
    }

    @GetMapping("/sin-stock")
    public ResponseEntity<List<Material>> obtenerSinStockPorId(@RequestParam List<Integer> ids) {
        return ResponseEntity.ok(materialServicio.obtenerSinStockPorId(ids));
    }
}
