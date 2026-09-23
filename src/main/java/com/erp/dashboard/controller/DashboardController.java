package com.erp.dashboard.controller;

import com.erp.sistema.seguridad.dto.UsuarioDTO;
import com.erp.sistema.seguridad.service.UsuarioService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    private final UsuarioService usuarioService;

    public DashboardController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/dashboard")
    public String mostrarDashboard(Model model) {
        // 1. Obtener el nombre del usuario actualmente logueado desde Spring Security
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        // 2. Pedirle los datos al Service (El Chef)
        UsuarioDTO datosUsuario = usuarioService.obtenerDatosUsuario(username);

        // 3. Pasar los datos seguros al Template (Thymeleaf)
        model.addAttribute("usuario", datosUsuario);

        // 4. Retornar el nombre del archivo HTML (sin la extensión .html)
        return "dashboard";
    }
}