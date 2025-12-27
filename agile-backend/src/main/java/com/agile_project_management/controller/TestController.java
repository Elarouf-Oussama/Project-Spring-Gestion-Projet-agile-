package com.agile_project_management.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/public/hello")
    public String sayHello() {
        return "Excellent ! Le serveur fonctionne et la sécurité est configurée.";
    }
}