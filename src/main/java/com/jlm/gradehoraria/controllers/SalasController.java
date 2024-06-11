package com.jlm.gradehoraria.controllers;

import com.jlm.gradehoraria.core.entity.Sala;
import com.jlm.gradehoraria.core.service.SalaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/inicio/salas")
public class SalasController {

    @Autowired
    private SalaService salaService;

    @Autowired
    @GetMapping
    public ModelAndView index(){
        ModelAndView view = new ModelAndView("salas/salas.html");
        view.addObject("salas", salaService.obterTodos());
        return view;
    }
}
