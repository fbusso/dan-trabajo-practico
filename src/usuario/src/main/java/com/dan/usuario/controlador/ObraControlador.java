package com.dan.usuario.controlador;

import com.dan.usuario.dominio.Obra;
import com.dan.usuario.dominio.TipoObra;
import com.dan.usuario.servicio.ObraServicio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/id/{id}")
    public ResponseEntity<Obra> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.of(obraServicio.obtenerPorId(id));
    }

    @GetMapping("/cliente-tipo")
    public ResponseEntity<List<Obra>> obtenerPorClienteOTipoObra(@RequestParam(required = false) Integer clienteId,
                                                                 @RequestParam(required = false) TipoObra tipoObra) {
        return ResponseEntity.ok(obraServicio.obtenerPorClienteIdOTipoObra(clienteId, tipoObra));
    }

    @DeleteMapping("/id/{id}")
    public HttpStatus eliminarPorId(@PathVariable Integer id) {
        obraServicio.eliminarPorId(id);
        return HttpStatus.NO_CONTENT;
    }
}
