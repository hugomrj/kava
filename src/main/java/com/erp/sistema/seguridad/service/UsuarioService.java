package com.erp.sistema.seguridad.service;

import com.erp.sistema.seguridad.dto.UsuarioDTO;
import com.erp.sistema.seguridad.entity.Usuario;
import com.erp.sistema.seguridad.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional(readOnly = true)
    public UsuarioDTO obtenerDatosUsuario(String username) {
        Usuario usuario = usuarioRepository.findByUsuarioAndEstado(username, 1)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado o inactivo: " + username));

        // --- INICIO: LOGS DE DEPURACIÓN ---
        System.out.println("===========================================");
        System.out.println("🔍 DEBUG USUARIO: " + usuario.getNombre() + " " + usuario.getApellido());
        System.out.println("🔍 DEBUG codigo_niveles (ID): " + usuario.getCodigoNiveles());
        System.out.println("🔍 DEBUG objeto Nivel: " + usuario.getNivel());

        if (usuario.getNivel() != null) {
            System.out.println("🔍 DEBUG Descripción del Nivel: " + usuario.getNivel().getDescripcion());
        } else {
            System.out.println("⚠️ WARNING: El objeto Nivel es NULL");
        }
        System.out.println("===========================================");
        // --- FIN: LOGS DE DEPURACIÓN ---

        String nombreNivel = "Sin Rol";

        if (usuario.getNivel() != null && usuario.getNivel().getDescripcion() != null) {
            nombreNivel = usuario.getNivel().getDescripcion();
        } else if (usuario.getCodigoNiveles() != null) {
            nombreNivel = "Nivel ID: " + usuario.getCodigoNiveles(); // Fallback si el objeto no cargó
        }

        return new UsuarioDTO(usuario.getNombre(), usuario.getApellido(), nombreNivel);
    }
}