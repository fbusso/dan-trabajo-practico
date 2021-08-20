package com.dan.pago.servicio;

import com.dan.pago.dominio.Carrito;
import com.mercadopago.MercadoPago;
import com.mercadopago.exceptions.MPConfException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Preference;
import com.mercadopago.resources.datastructures.preference.Item;
import org.springframework.stereotype.Service;

@Service
public class MercadoPagoServicio {

    MercadoPagoServicio() throws MPConfException {
        MercadoPago.SDK.setAccessToken("TEST-4534020504017154-032005-7a7bd282b7b0b8c56fe7c965767bb0f4-226979380");
    }

    public String generarLinkPago(Carrito carrito) throws MPException {
        Preference preference = new Preference();

        carrito.getItemPedidos().forEach(pedido -> {
            Item item = new Item();
            item.setTitle(pedido.getNombre())
                    .setQuantity(pedido.getCantidad())
                    .setUnitPrice(pedido.getPrecio());
            preference.appendItem(item);
        });

        preference.save();

        return preference.getSandboxInitPoint();
    }
}
