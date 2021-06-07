package com.dan.producto.dto;

import java.time.LocalDate;
import java.util.List;

public class PedidoDto {

    private Integer id;
    private LocalDate fechaPedido;
    private String estadoPedido;
    List<DetallePedidoDto> detallePedido;

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

    public String getEstadoPedido() {
        return estadoPedido;
    }

    public void setEstadoPedido(String estadoPedido) {
        this.estadoPedido = estadoPedido;
    }

    public List<DetallePedidoDto> getDetallePedido() {
        return detallePedido;
    }

    public void setDetallePedido(List<DetallePedidoDto> detallePedido) {
        this.detallePedido = detallePedido;
    }
}
