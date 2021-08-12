package com.dan.usuario.servicio;

import com.dan.usuario.dominio.Obra;
import com.dan.usuario.dominio.TipoObra;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ObraServicio {

    Obra crear(Obra obra, Integer clienteId);

    Optional<Obra> obtenerPorId(Integer id);

    Page<Obra> obtenerTodos(Integer pagina, Integer cantidadRegistros);

    List<Obra> obtenerPorClienteIdOTipoObra(Integer clienteId, TipoObra tipoObra);

    void eliminarPorId(Integer id);
}
