package com.dan.pago.dominio;

import java.time.Instant;
import java.util.List;

public class Carrito {
    public List<ItemPedido> itemPedidos;
    public Instant fechaHora;

    public List<ItemPedido> getItemPedidos() {
        return itemPedidos;
    }

    public void setItemPedidos(List<ItemPedido> itemPedidos) {
        this.itemPedidos = itemPedidos;
    }

    public Instant getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Instant fechaHora) {
        this.fechaHora = fechaHora;
    }
}
