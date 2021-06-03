package com.dan.usuario.repositorio;

import com.dan.usuario.dominio.Obra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObraRepositorio extends JpaRepository<Obra, Integer> {
}
