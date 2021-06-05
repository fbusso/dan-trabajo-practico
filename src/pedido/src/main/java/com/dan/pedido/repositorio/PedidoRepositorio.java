package com.dan.pedido.repositorio;

import com.dan.pedido.dominio.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepositorio extends JpaRepository<Pedido, Integer> {

    List<Pedido> findAllByObra_clienteId(Integer clienteId);
}
