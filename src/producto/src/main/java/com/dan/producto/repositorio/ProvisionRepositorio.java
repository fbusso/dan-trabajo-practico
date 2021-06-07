package com.dan.producto.repositorio;

import com.dan.producto.dominio.Provision;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvisionRepositorio extends JpaRepository<Provision, Integer> {
}
