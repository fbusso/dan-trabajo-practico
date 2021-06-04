package com.dan.usuario.excepcion;

import com.dan.usuario.dto.SituacionCrediticiaDto;
import com.sun.istack.NotNull;
import org.springframework.util.StringUtils;

public class SituacionCrediticiaExcepcion extends ReglaDeNegociosExcepcion{

    public SituacionCrediticiaExcepcion() {
        super("No es posible comunicarse con la Central de Deudores.");
    }

    public SituacionCrediticiaExcepcion(@NotNull SituacionCrediticiaDto situacionCrediticiaDto) {
        super("No es posible dar de alta un Cliente de Situación Crediticia " + StringUtils.capitalize(situacionCrediticiaDto.name().toLowerCase()) + ".");
    }
}
