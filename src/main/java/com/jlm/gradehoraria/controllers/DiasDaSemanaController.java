package com.jlm.gradehoraria.controllers;

import com.jlm.gradehoraria.core.entity.DiaDaSemana;
import com.jlm.gradehoraria.core.service.DiaDaSemanaService;
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
@RequestMapping("/inicio/dias-da-semana")
public class DiasDaSemanaController {

    @Autowired
    private DiaDaSemanaService diaDaSemanaService;

    @Autowired
    @GetMapping
    public ModelAndView index(){
        ModelAndView view = new ModelAndView("dias-da-semana/dias-da-semana.html");
        view.addObject("semana", diaDaSemanaService.obterTodos());
        return view;
    }
}
