package com.dan.pedido.servicio;

import com.dan.pedido.dto.SituacionCrediticiaDto;
import com.dan.pedido.excepcion.ReglaDeNegociosExcepcion;

public interface BcraServicio {

    SituacionCrediticiaDto obtenerSituacionCrediticiaPorCuit(String cuit) throws ReglaDeNegociosExcepcion;
}
