package com.dan.usuario.servicio;

import com.dan.usuario.dto.SituacionCrediticiaDto;
import com.dan.usuario.excepcion.ReglaDeNegociosExcepcion;

public interface BcraServicio {

    SituacionCrediticiaDto obtenerSituacionCrediticiaPorCuit(String cuit) throws ReglaDeNegociosExcepcion;
}
