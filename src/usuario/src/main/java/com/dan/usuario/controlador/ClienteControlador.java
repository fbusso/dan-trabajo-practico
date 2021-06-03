package com.dan.usuario.controlador;

import com.dan.usuario.dominio.Cliente;
import com.dan.usuario.servicio.ClienteServicio;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteControlador {

    private final ClienteServicio clienteServicio;

    public ClienteControlador(ClienteServicio clienteServicio) {
        this.clienteServicio = clienteServicio;
    }

    @PostMapping
    public ResponseEntity<Cliente> crear(@RequestBody Cliente cliente) {
        return new ResponseEntity<>(clienteServicio.crear(cliente), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> obtenerTodos() {
        return ResponseEntity.ok(clienteServicio.obtenerTodos());
    }

    @GetMapping("/cuit/{cuit}")
    public ResponseEntity<Cliente> obtenerPorCuit(@PathVariable String cuit) {
        return ResponseEntity.of(clienteServicio.buscarPorCuit(cuit));
    }

    @GetMapping("/razon-social")
    public ResponseEntity<List<Cliente>> obtenerPorRazonSocial(@RequestParam(required = false) String razonSocial) {

        List<Cliente> clientes = StringUtils.isBlank(razonSocial)
                ? clienteServicio.obtenerTodos()
                : clienteServicio.buscarPorRazonSocial(razonSocial);

        return ResponseEntity.ok(clientes);
    }
}
