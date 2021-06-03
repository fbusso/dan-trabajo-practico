package com.dan.usuario.controlador;

import com.dan.usuario.dominio.Obra;
import com.dan.usuario.servicio.ObraServicio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/obras")
public class ObraControlador {

    private final ObraServicio obraServicio;

    public ObraControlador(ObraServicio obraServicio) {
        this.obraServicio = obraServicio;
    }

    @PostMapping
    public ResponseEntity<Obra> crear(@RequestBody Obra obra, @RequestParam Integer clienteId) {
        return new ResponseEntity<>(obraServicio.crear(obra, clienteId), HttpStatus.CREATED);
    }

    @GetMapping("/{obraId}")
    public ResponseEntity<Obra> obtenerPorId(@PathVariable Integer obraId) {
        return ResponseEntity.of(obraServicio.obtenerPorId(obraId));
    }
}
