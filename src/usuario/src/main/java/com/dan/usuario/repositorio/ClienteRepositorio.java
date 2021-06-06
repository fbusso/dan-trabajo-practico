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

    List<Cliente> findAllByFechaBajaNotNull();

    List<Cliente> findAllByRazonSocialLikeAndFechaBajaNotNull(String razonSocial);

    Optional<Cliente> findFirstByCuitAndFechaBajaNotNull(String cuit);

    @Query(value = "SELECT * FROM cliente INNER JOIN obra o on cliente.id = o.cliente_id AND o.id = :obraId LIMIT 1;", nativeQuery = true)
    Optional<Cliente> obtenerPorObraId(@Param("obraId")Integer obraId);
}
