package com.dan.pedido.repositorio;

import com.dan.pedido.dominio.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepositorio extends JpaRepository<Producto, Integer> {
}
