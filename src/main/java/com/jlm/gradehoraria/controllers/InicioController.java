package com.jlm.gradehoraria.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InicioController {

    @GetMapping("inicio")
    public String index() {
        return "/inicio.html";
    }

    @GetMapping("inicio/sair")
    public String sair() {
        return "/sair.html";
    }
}
