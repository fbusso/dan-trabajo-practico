package com.dan.usuario.controlador;

import com.dan.usuario.dominio.Empleado;
import com.dan.usuario.excepcion.ReglaDeNegociosExcepcion;
import com.dan.usuario.servicio.EmpleadoServicio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/empleados")
public class EmpleadoControlador {

    private final EmpleadoServicio empleadoServicio;

    public EmpleadoControlador(EmpleadoServicio empleadoServicio) {
        this.empleadoServicio = empleadoServicio;
    }

    @PostMapping
    public ResponseEntity<Empleado> crear(@RequestBody Empleado empleado) throws ReglaDeNegociosExcepcion {
        return new ResponseEntity<>(empleadoServicio.crear(empleado), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Empleado> actualizar(@RequestBody Empleado empleado) {
        return new ResponseEntity<>(empleadoServicio.actualizar(empleado), HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Empleado> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.of(empleadoServicio.obtenerPorId(id));
    }

    @DeleteMapping("/id/{id}")
    public HttpStatus eliminarPorId(@PathVariable Integer id) {
        empleadoServicio.eliminarPorId(id);
        return HttpStatus.NO_CONTENT;
    }
}
