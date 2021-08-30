package com.dan.pago.servicio;

import com.dan.pago.dominio.Preferencia;
import com.dan.pago.repositorio.PreferenciaRepositorio;
import org.springframework.stereotype.Service;

@Service
public class PreferenciaServicio {
    public final PreferenciaRepositorio preferenciaRepositorio;

    public PreferenciaServicio(PreferenciaRepositorio preferenciaRepositorio) {
        this.preferenciaRepositorio = preferenciaRepositorio;
    }

    public Preferencia save(Preferencia preferencia) {
        return this.preferenciaRepositorio.save(preferencia);
    }
}
