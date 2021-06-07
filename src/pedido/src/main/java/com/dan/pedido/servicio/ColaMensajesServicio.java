package com.dan.pedido.servicio;

import com.dan.pedido.excepcion.ColaDeMensajesExcepcion;

public interface ColaMensajesServicio {

    void enviar(Object mensaje) throws ColaDeMensajesExcepcion;
}
