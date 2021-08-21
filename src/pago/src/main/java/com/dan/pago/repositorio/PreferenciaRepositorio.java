package com.dan.pago.repositorio;

import com.dan.pago.dominio.Preferencia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PreferenciaRepositorio extends JpaRepository<Preferencia, Integer> {
}
