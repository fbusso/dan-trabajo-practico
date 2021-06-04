package com.dan.usuario.servicio;

import com.dan.usuario.dto.SituacionCrediticiaDto;

public interface BcraServicio {

    SituacionCrediticiaDto obtenerSituacionCrediticiaPorCuit(String cuit);
}
