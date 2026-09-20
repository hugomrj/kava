package com.example.kava.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/dashboard")
    public String showDashboard() {
        // Spring Boot buscará automáticamente "dashboard.html" en src/main/resources/templates/
        // y Thymeleaf procesará el fragmento del menú.
        return "dashboard";
    }
}