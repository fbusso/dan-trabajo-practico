package com.dan.bcra.dominio;

import java.util.Arrays;
import java.util.List;

public enum SituacionCrediticia {
    NORMAL, BAJO, MEDIO, ALTO, IRRECUPERABLE, IRRECUPERABLE_POR_DISPOSICION_TECNICA;

    public static List<SituacionCrediticia> obtenerTodos() {
        return Arrays.asList(NORMAL, BAJO, MEDIO, ALTO, IRRECUPERABLE, IRRECUPERABLE_POR_DISPOSICION_TECNICA);
    }
}
