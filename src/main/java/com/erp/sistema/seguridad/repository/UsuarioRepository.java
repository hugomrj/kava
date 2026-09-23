package com.erp.sistema.seguridad.repository;

import com.erp.sistema.seguridad.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByUsuarioAndEstado(String usuario, Integer estado);
}