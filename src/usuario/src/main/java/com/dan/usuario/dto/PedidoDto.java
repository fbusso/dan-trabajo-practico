package com.dan.usuario.dto;

import java.time.LocalDate;

public class PedidoDto {

    private Integer obraId;
    private EstadoPedidoDto estadoPedido;
    private LocalDate fechaPedido;

    public Integer getObraId() {
        return obraId;
    }

    public void setObraId(Integer obraId) {
        this.obraId = obraId;
    }

    public EstadoPedidoDto getEstadoPedido() {
        return estadoPedido;
    }

    public void setEstadoPedido(EstadoPedidoDto estadoPedido) {
        this.estadoPedido = estadoPedido;
    }

    public LocalDate getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(LocalDate fechaPedido) {
        this.fechaPedido = fechaPedido;
    }
}
