package com.dan.usuario.repositorio;

import com.dan.usuario.dominio.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findTopByNombreUsuario(String nombreUsuario);
}
