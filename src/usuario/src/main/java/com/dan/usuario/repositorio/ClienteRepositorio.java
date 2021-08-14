package com.dan.usuario.repositorio;

import com.dan.usuario.dominio.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente, Integer> {

    List<Cliente> findAllByFechaBajaIsNull();

    List<Cliente> findAllByRazonSocialLikeAndFechaBajaIsNull(String razonSocial);

    Optional<Cliente> findFirstByCuitAndFechaBajaIsNull(String cuit);

    Optional<Cliente> findByIdAndFechaBajaIsNull(Integer id);

    @Query("SELECT c FROM Cliente c JOIN Obra o ON o.cliente = c AND o.id = :obraId AND c.fechaBaja IS NULL")
    Optional<Cliente> obtenerPorObraId(@Param("obraId")Integer obraId);
}

