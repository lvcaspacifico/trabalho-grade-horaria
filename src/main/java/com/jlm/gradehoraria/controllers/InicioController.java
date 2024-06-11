package com.jlm.gradehoraria.controllers;

import com.jlm.gradehoraria.core.service.InicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class InicioController {

    @Autowired
    InicioService inicioService;

    @GetMapping("inicio")
    public ModelAndView index(){
        ModelAndView view = new ModelAndView("/inicio.html");
        // obter valores
        long professores = inicioService.obterProfessores();
        long disciplinas = inicioService.obterDisciplinas();
        long turmas = inicioService.obterTurmas();
        long salas = inicioService.obterSalas();
        // passar valores
        view.addObject("professores", professores);
        view.addObject("disciplinas", disciplinas);
        view.addObject("turmas", turmas);
        view.addObject("salas", salas);
        return view;
    }

    @GetMapping("inicio/sair")
    public String sair() {
        return "/sair.html";
    }
}
