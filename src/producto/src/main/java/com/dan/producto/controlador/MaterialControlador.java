package com.dan.producto.controlador;

import com.dan.producto.dominio.Material;
import com.dan.producto.servicio.MaterialServicio;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/material")
public class MaterialControlador {

    private final MaterialServicio materialServicio;

    public MaterialControlador(MaterialServicio materialServicio) {
        this.materialServicio = materialServicio;
    }

    @PostMapping()
    public ResponseEntity<Material> crear(@RequestBody Material material) {
        return new ResponseEntity<>(materialServicio.crear(material), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<Page<Material>> obtenerTodos(@RequestParam(required = false) Integer page,
                                                       @RequestParam(required = false) Integer size) {
        final Integer pagina = Optional.ofNullable(page).orElse(0);
        final Integer cantidadRegistros = Optional.ofNullable(size).orElse(Integer.MAX_VALUE);
        return ResponseEntity.ok(materialServicio.obtenerTodos(pagina, cantidadRegistros));
    }

    @GetMapping("/sin-stock")
    public ResponseEntity<List<Material>> obtenerSinStockPorId(@RequestParam List<Integer> ids) {
        return ResponseEntity.ok(materialServicio.obtenerSinStockPorId(ids));
    }
}
