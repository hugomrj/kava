package com.erp.sistema.seguridad.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public ResponseEntity<Map<String, Object>> login(@RequestParam String username, @RequestParam String password) {
        Map<String, Object> response = new HashMap<>();

        try {
            // 1. Validar credenciales contra la BD
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            // 2. Establecer en el contexto.
            // ¡Spring Security guarda esto automáticamente en la cookie JSESSIONID al finalizar esta petición!
            SecurityContextHolder.getContext().setAuthentication(authentication);

            System.out.println("✅ LOGIN EXITOSO PARA: " + username);

            response.put("success", true);
            response.put("redirectUrl", "/dashboard");
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
    public ResponseEntity<Map<String, Object>> logout() {
        SecurityContextHolder.clearContext();
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("redirectUrl", "/login");
        return ResponseEntity.ok(response);
    }
}