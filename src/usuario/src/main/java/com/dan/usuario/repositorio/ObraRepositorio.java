package com.dan.usuario.repositorio;

import com.dan.usuario.dominio.Obra;
import com.dan.usuario.dominio.TipoObra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ObraRepositorio extends JpaRepository<Obra, Integer> {

    List<Obra> findAllByCliente_IdOrTipoObra(Integer clienteId, TipoObra tipoObra);
}
