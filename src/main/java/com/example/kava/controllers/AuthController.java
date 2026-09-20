package com.example.kava.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    // 1. Ruta inicial: Muestra el Login
    @GetMapping("/")
    public String showLogin() {
        return "login"; // Busca src/main/resources/templates/login.html
    }



    // 3. Procesamiento del Login con HTMX
    @PostMapping("/auth/login")
    public ResponseEntity<String> processLogin(
            @RequestParam String username,
            @RequestParam String password) {

        // --- LÓGICA MOCK (Aquí luego conectarás tu Base de Datos / Spring Security) ---
        boolean isValid = "admin".equals(username) && "admin".equals(password);

        if (isValid) {
            // ÉXITO: Le decimos a HTMX que redirija el navegador al dashboard.
            // HTMX intercepta este header y hace un window.location = '/dashboard'
            return ResponseEntity.ok()
                    .header("HX-Redirect", "/dashboard")
                    .body("");
        } else {
            // FALLO: Devolvemos un fragmento HTML.
            // HTMX lo inyectará dentro del div #error-container sin recargar la página.
            String errorHtml = "<div class='alert'>" +
                    "<svg width='18' height='18' viewBox='0 0 24 24' fill='none' stroke='currentColor' stroke-width='2'><circle cx='12' cy='12' r='10'/><path d='M12 8v4M12 16h.01'/></svg>" +
                    "<span>Credenciales incorrectas. Intenta de nuevo.</span>" +
                    "</div>";
            return ResponseEntity.badRequest().body(errorHtml);
        }
    }

    // 4. Logout
    @PostMapping("/auth/logout")
    public ResponseEntity<String> logout() {
        return ResponseEntity.ok()
                .header("HX-Redirect", "/")
                .body("");
    }
}