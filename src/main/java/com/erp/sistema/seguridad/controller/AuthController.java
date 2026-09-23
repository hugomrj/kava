package com.erp.sistema.seguridad.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class AuthController {

    private final AuthenticationManager authenticationManager;

    public AuthController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/auth/login")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> login(
            @RequestParam String username,
            @RequestParam String password,
            HttpServletRequest request) { // <-- 1. Agregar HttpServletRequest

        Map<String, Object> response = new HashMap<>();

        try {
            // 2. Validar credenciales
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            // 3. Establecer en el contexto de seguridad
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // 4. ¡CRÍTICO! Forzar la creación de la sesión y guardar el contexto manualmente
            HttpSession session = request.getSession(true);
            session.setAttribute(
                    HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                    SecurityContextHolder.getContext()
            );

            System.out.println("✅ LOGIN EXITOSO PARA: " + username);
            System.out.println("🍪 SESSION ID CREADO: " + session.getId());

            response.put("success", true);
            response.put("redirectUrl", "/dashboard"); // Asegúrate que tu controller del dashboard sea @GetMapping("/dashboard")
            return ResponseEntity.ok(response);

        } catch (AuthenticationException e) {
            System.out.println("❌ LOGIN FALLIDO PARA: " + username);
            response.put("success", false);
            response.put("message", "Usuario o contraseña incorrectos.");
            return ResponseEntity.status(401).body(response);
        }
    }

    @PostMapping("/auth/logout")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> logout(HttpServletRequest request) {
        SecurityContextHolder.clearContext();
        request.getSession().invalidate(); // Invalidar la sesión al cerrar

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("redirectUrl", "/login");
        return ResponseEntity.ok(response);
    }
}