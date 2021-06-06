package com.dan.pedido.servicio;

import com.dan.pedido.excepcion.ReglaDeNegociosExcepcion;

import java.util.Map;

public interface HttpServicio {

    <T> T get(Class<T> tipo , String url, Object ...args) throws ReglaDeNegociosExcepcion;

    <T> T get(Class<T> tipo , String url, Map<String, ?> parametros) throws ReglaDeNegociosExcepcion;
}
