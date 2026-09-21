package com.erp.sistema.seguridad.repository;

import com.erp.sistema.seguridad.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    // Busca un usuario por su nombre de usuario y que esté activo (Estado = 1)
    Optional<Usuario> findByUsuarioAndEstado(String usuario, Integer estado);
}