package com.dan.producto.repositorio;

import com.dan.producto.dominio.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaterialRepositorio extends JpaRepository<Material, Integer> {

    List<Material> findAllByIdIsInAndStockActual(List<Integer> ids, Integer stockActual);
}
