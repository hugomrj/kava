package com.erp.sistema.seguridad.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/password")
public class PasswordController {

    private final PasswordEncoder passwordEncoder;

    public PasswordController(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/hash")
    public String hash(@RequestParam String password) {
        return passwordEncoder.encode(password);
    }
}



/*
curl -X POST "http://localhost:8080/admin/password/hash?password=123456"

 */