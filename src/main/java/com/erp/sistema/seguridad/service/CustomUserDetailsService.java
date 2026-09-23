package com.erp.sistema.seguridad.service;

import com.erp.sistema.seguridad.entity.Usuario;
import com.erp.sistema.seguridad.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // <-- IMPORTANTE

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public CustomUserDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    @Transactional(readOnly = true) // <-- ESTA LÍNEA SOLUCIONA EL "NO SESSION"
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = usuarioRepository.findByUsuarioAndEstado(username, 1)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado o inactivo: " + username));

        // Ahora Hibernate puede acceder a getNivel() porque la transacción está abierta
        String rol = "USER"; // Valor por defecto
        if (usuario.getNivel() != null && usuario.getNivel().getDescripcion() != null) {
            rol = usuario.getNivel().getDescripcion();
        }

        return User.builder()
                .username(usuario.getUsuario())
                .password(usuario.getPassword())
                .roles(rol)
                .build();
    }
}