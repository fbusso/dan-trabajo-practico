package com.dan.pedido.controlador;

import com.dan.pedido.dominio.DetallePedido;
import com.dan.pedido.dominio.Pedido;
import com.dan.pedido.excepcion.ReglaDeNegociosExcepcion;
import com.dan.pedido.servicio.DetallePedidoServicio;
import com.dan.pedido.servicio.PedidoServicio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/pedido")
public class PedidoControlador {

    private final PedidoServicio pedidoServicio;
    private final DetallePedidoServicio detallePedidoServicio;

    public PedidoControlador(PedidoServicio pedidoServicio, DetallePedidoServicio detallePedidoServicio) {
        this.pedidoServicio = pedidoServicio;
        this.detallePedidoServicio = detallePedidoServicio;
    }

    @PostMapping
    public ResponseEntity<Pedido> crear(@RequestBody Pedido pedido) throws ReglaDeNegociosExcepcion {
        return new ResponseEntity<>(pedidoServicio.crear(pedido), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.of(pedidoServicio.obtenerPorId(id));
    }

    @GetMapping("/cliente/{id}")
    public ResponseEntity<List<Pedido>> obtenerPorClienteId(@PathVariable Integer id) {
        return ResponseEntity.ok(pedidoServicio.obtenerPorClienteId(id));
    }

    @PatchMapping("/{pedidoId}/detalle")
    public ResponseEntity<Pedido> agregarDetalle(@PathVariable Integer pedidoId,
                                                 @RequestBody DetallePedido detallePedido) {

        return pedidoServicio.agregarDetalle(pedidoId, detallePedido)
                .map(entidad -> new ResponseEntity<>(entidad, HttpStatus.CREATED))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<Pedido> confirmar(@PathVariable Integer id) throws ReglaDeNegociosExcepcion {
        return ResponseEntity.ok(pedidoServicio.confirmar(id));
    }

    @DeleteMapping("/{id}")
    public HttpStatus eliminarPorId(@PathVariable Integer id) {
        pedidoServicio.eliminarPorId(id);
        return HttpStatus.NO_CONTENT;
    }

    @DeleteMapping("/detalle/{detallePedidoId}")
    public HttpStatus eliminarDetallePorId(@PathVariable Integer detallePedidoId) {
        detallePedidoServicio.eliminarPorId(detallePedidoId);
        return HttpStatus.NO_CONTENT;
    }
}
