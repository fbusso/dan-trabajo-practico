package com.dan.pago.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class PedidoDto {
    private Integer id;
    private LocalDate fechaPedido;
    private EstadoPedidoDto estadoPedido;
    List<DetallePedidoDto> detallePedido;
    private ObraDto obra;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(LocalDate fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public EstadoPedidoDto getEstadoPedido() {
        return estadoPedido;
    }

    public void setEstadoPedido(EstadoPedidoDto estadoPedido) {
        this.estadoPedido = estadoPedido;
    }

    public List<DetallePedidoDto> getDetallePedido() {
        return detallePedido;
    }

    public void setDetallePedido(List<DetallePedidoDto> detallePedido) {
        this.detallePedido = detallePedido;
    }

    public ObraDto getObra() {
        return obra;
    }

    public void setObra(ObraDto obra) {
        this.obra = obra;
    }

    public BigDecimal getCostoTotal() {
        return getDetallePedido()
                .stream()
                .map(detalle -> detalle.getPrecio().multiply(BigDecimal.valueOf(detalle.getCantidad())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
