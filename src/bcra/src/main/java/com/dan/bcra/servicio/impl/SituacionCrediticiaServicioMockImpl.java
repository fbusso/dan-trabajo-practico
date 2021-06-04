package com.dan.bcra.servicio.impl;

import com.dan.bcra.dominio.SituacionCrediticia;
import com.dan.bcra.servicio.SituacionCrediticiaServicioMock;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class SituacionCrediticiaServicioMockImpl implements SituacionCrediticiaServicioMock {

    final List<SituacionCrediticia> riesgos = SituacionCrediticia.obtenerTodos();

    @Override
    public SituacionCrediticia obtenerPorCuit(String cuit) {
        Collections.shuffle(riesgos);
        return riesgos.get(0);
    }
}
