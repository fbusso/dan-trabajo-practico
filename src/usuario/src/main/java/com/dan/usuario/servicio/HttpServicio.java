package com.dan.usuario.servicio;

import com.dan.usuario.excepcion.ReglaDeNegociosExcepcion;

public interface HttpServicio {

    /**
     * Realiza una petición HTTP GET.
     * @param tipo tipo de dato al que se quiere convertir la respuesta de la petición
     * @param url URL a la que se quiere realizar la petición
     * @param args argumentos de la solicitud
     * @return resultado de la solicitud
     * @throws ReglaDeNegociosExcepcion si existe un error de conexión
     */
    <T> T get(Class<T> tipo, String url, Object ...args) throws ReglaDeNegociosExcepcion;
}
