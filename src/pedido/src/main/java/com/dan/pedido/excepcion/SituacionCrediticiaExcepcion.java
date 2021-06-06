package com.dan.pedido.excepcion;

public class SituacionCrediticiaExcepcion extends ReglaDeNegociosExcepcion {

    public SituacionCrediticiaExcepcion() {
        super("No es posible confirmar el pedido debido a la situación crediticia del solicitante.");
    }
}
