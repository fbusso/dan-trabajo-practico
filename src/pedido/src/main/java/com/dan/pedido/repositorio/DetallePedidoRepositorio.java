package com.dan.pedido.repositorio;

import com.dan.pedido.dominio.DetallePedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetallePedidoRepositorio extends JpaRepository<DetallePedido, Integer> {
}
