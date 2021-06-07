package com.dan.pedido.excepcion;

public class ColaDeMensajesExcepcion extends ReglaDeNegociosExcepcion {

    public ColaDeMensajesExcepcion() {
        super("No fue posible comunicarse con la Cola de Mensajes");
    }
}
