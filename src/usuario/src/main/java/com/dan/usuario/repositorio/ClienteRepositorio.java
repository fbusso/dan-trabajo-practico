package com.dan.usuario.repositorio;

import com.dan.usuario.dominio.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente, Integer> {

    List<Cliente> findAllByRazonSocialLike(String razonSocial);

    Optional<Cliente> findFirstByCuit(String cuit);
}
