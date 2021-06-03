package com.dan.usuario.servicio;

import com.dan.usuario.dominio.Obra;

import java.util.Optional;

public interface ObraServicio {

    Obra crear(Obra obra, Integer clienteId);

    Optional<Obra> obtenerPorId(Integer obraId);
}
