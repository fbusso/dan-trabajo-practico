package com.dan.pago.servicio;

import com.dan.pago.dominio.Preferencia;
import com.dan.pago.dto.PedidoDto;
import com.mercadopago.MercadoPago;
import com.mercadopago.exceptions.MPConfException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Preference;
import com.mercadopago.resources.datastructures.preference.Item;
import org.springframework.stereotype.Service;

@Service
public class MercadoPagoServicio {
    public final PreferenciaServicio preferenciaServicio;

    MercadoPagoServicio(PreferenciaServicio preferenciaServicio) throws MPConfException {
        this.preferenciaServicio = preferenciaServicio;
        MercadoPago.SDK.setAccessToken("TEST-4534020504017154-032005-7a7bd282b7b0b8c56fe7c965767bb0f4-226979380");
    }

    public String generarPreferencia(PedidoDto pedidoDto) throws MPException {
        Preference preference = new Preference();

        pedidoDto.getDetallePedido().forEach(pedido -> {
            Item item = new Item();
            item.setTitle(pedido.getProducto().getDescripcion())
                    .setQuantity(pedido.getCantidad())
                    .setUnitPrice(pedido.getPrecio().floatValue());
            preference.appendItem(item);
        });

        preference.save();

        String preferenceUrl = preference.getSandboxInitPoint();

        Preferencia preferencia = new Preferencia(preferenceUrl);
        this.preferenciaServicio.save(preferencia);

        return preferenceUrl;
    }
}
