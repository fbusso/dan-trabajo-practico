package com.dan.usuario.dto;

public enum SituacionCrediticiaDto {
    NORMAL, BAJO, MEDIO, ALTO, IRRECUPERABLE, IRRECUPERABLE_POR_DISPOSICION_TECNICA;

    /**
     * Determina si la Situación Crediticia del Cliente es apta para darlo de alta
     * @param situacionCrediticia: Situación crediticia a Evaluar
     */
    public static Boolean esSituacionValida(SituacionCrediticiaDto situacionCrediticia) {
        return SituacionCrediticiaDto.NORMAL.equals(situacionCrediticia) || SituacionCrediticiaDto.BAJO.equals(situacionCrediticia);
    }
}
