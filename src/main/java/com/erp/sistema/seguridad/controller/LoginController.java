package com.erp.sistema.seguridad.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    // 1. Si alguien entra a la raíz "/", lo mandamos al login
    @GetMapping("/")
    public String index() {
        return "redirect:/login";
    }

    // 2. Si alguien entra a "/login", mostramos la plantilla login.html
    @GetMapping("/login")
    public String login() {
        return "login";
    }
}