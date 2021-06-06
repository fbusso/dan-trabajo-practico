package com.dan.cuenta.repositorio;

import com.dan.cuenta.dominio.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagoRepositorio extends JpaRepository<Pago, Integer> {
}
