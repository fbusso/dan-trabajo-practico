package com.dan.usuario.servicio;

public interface HttpServicio {

    <T> T get(Class<T> cast, String url, Object ...args);
}
