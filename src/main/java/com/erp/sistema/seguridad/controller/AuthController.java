package com.erp.sistema.seguridad.controller;

import com.erp.sistema.seguridad.entity.Usuario;
import com.erp.sistema.seguridad.service.AuthService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @PostMapping("/auth/login")
    public ResponseEntity<String> login(@RequestParam String username,
                                        @RequestParam String password,
                                        HttpSession session) {

        Optional<Usuario> opt = authService.login(username, password);

        if (opt.isPresent()) {
            session.setAttribute("usuarioLogueado", opt.get());

            return ResponseEntity.ok()
                    .header("HX-Redirect", "/dashboard")
                    .body("");
        }

        String errorHtml = "<div class='alert'>" +
                "<svg width='18' height='18' viewBox='0 0 24 24' fill='none' " +
                "stroke='currentColor' stroke-width='2'>" +
                "<circle cx='12' cy='12' r='10'/>" +
                "<path d='M12 8v4M12 16h.01'/>" +
                "</svg>" +
                "<span>Credenciales incorrectas. Intenta de nuevo.</span>" +
                "</div>";

        return ResponseEntity.badRequest().body(errorHtml);
    }



    @PostMapping("/auth/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok()
                .header("HX-Redirect", "/login")
                .body("");
    }
}