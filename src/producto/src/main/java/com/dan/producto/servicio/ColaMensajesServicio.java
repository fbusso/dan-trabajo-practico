package com.dan.producto.servicio;

import com.dan.producto.dto.PedidoDto;

public interface ColaMensajesServicio {

    void mensajeRecibido(PedidoDto pedido);
}
