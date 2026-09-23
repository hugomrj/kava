package com.erp.dashboard.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        System.out.println("=== INTENTO DE ACCESO AL DASHBOARD ===");
        if (authentication != null && authentication.isAuthenticated()) {
            System.out.println("✅ AUTENTICADO COMO: " + authentication.getName());
            model.addAttribute("usuario", authentication.getName());
        } else {
            System.out.println("❌ NO AUTENTICADO (Por eso te da 403)");
        }

        return "dashboard";
    }
}