package com.erp.sistema.seguridad.service;

import com.erp.sistema.seguridad.entity.Usuario;
import com.erp.sistema.seguridad.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;

    public AuthService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    /**
     * Valida las credenciales del usuario.
     * Nota: Compara directamente con la columna 'Clave' de tu BD.
     */
    public Optional<Usuario> login(String usuario, String clave) {
        return usuarioRepository.findByUsuarioAndEstado(usuario, 1)
                .filter(u -> u.getClave().equals(clave));
    }
}