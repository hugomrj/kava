package com.erp.sistema.seguridad.service;

import com.erp.sistema.seguridad.entity.Usuario;
import com.erp.sistema.seguridad.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public CustomUserDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 1. Buscamos el usuario en tu BD (Estado = 1 significa activo)
        Usuario usuario = usuarioRepository.findByUsuarioAndEstado(username, 1)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado o inactivo: " + username));

        // 2. Lo convertimos al formato que Spring Security entiende
        return User.builder()
                .username(usuario.getUsuario())
                .password(usuario.getPassword()) // El hash BCrypt de tu BD
                // Si tienes roles, aquí los asignarías. Por ahora le damos el nivel como rol.
                .roles(usuario.getNivel() != null ? usuario.getNivel().getDescripcion() : "USER")
                .build();
    }
}