package com.dan.pedido.controlador;

import com.dan.pedido.dominio.DetallePedido;
import com.dan.pedido.dominio.Pedido;
import com.dan.pedido.excepcion.ReglaDeNegociosExcepcion;
import com.dan.pedido.servicio.DetallePedidoServicio;
import com.dan.pedido.servicio.PedidoServicio;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
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

    @GetMapping()
    public  ResponseEntity<Page<Pedido>> obtenerTodos(@RequestParam(required = false) Integer page,
                                                      @RequestParam(required = false) Integer size) {
        final Integer pagina = Optional.ofNullable(page).orElse(0);
        final Integer cantidadRegistros = Optional.ofNullable(size).orElse(Integer.MAX_VALUE);
        return ResponseEntity.ok(pedidoServicio.obtenerTodos(pagina, cantidadRegistros));
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
