package com.jlm.gradehoraria.controllers;

import com.jlm.gradehoraria.core.entity.Professor;
import com.jlm.gradehoraria.core.service.ProfessoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/inicio/professores")
public class ProfessoresController {

    @Autowired
    private ProfessoresService professoresService;

    @GetMapping
    public ModelAndView index(){
        ModelAndView view = new ModelAndView("professores/professores.html");
        view.addObject("entidades",professoresService.obterTodos());
        return view;
    }

    @GetMapping("/{id}")
    public ModelAndView visualizar(@PathVariable("id") long id){
        ModelAndView view = new ModelAndView("professores/visualizar.html");
        var opt = professoresService.obterPeloId(id);
        opt.ifPresent(entidade -> view.addObject("entidade", entidade));
        return view;
    }
    @GetMapping("/{id}/editar-professor")
    public ModelAndView editar(@PathVariable("id") long id){
        ModelAndView view = new ModelAndView("professores/editar-professor.html");
        var opt = professoresService.obterPeloId(id);
        opt.ifPresent(entidade -> view.addObject("entidade", entidade));
        return view;
    }
    @GetMapping("/novo-professor") // ESSE É GET !!!!!
    public ModelAndView criarNovoProfessor(){
        ModelAndView view = new ModelAndView("professores/novo-professor.html");
        view.addObject("entidade", new Professor());
        return view;
    }

    @PostMapping("/novo-professor") // ESSE É POST !!!!!
    public ModelAndView criarNovoProfessor(@ModelAttribute("entidade") Professor professor){
        try {
            System.out.println(professor);
            professor.setId(0);
            professoresService.salvar(professor);
            return new ModelAndView("redirect:/inicio/professores/"+professor.getId());
        }catch (Exception e){
            ModelAndView model = new ModelAndView("professores/novo-professor.html");
            model.addObject("erro",e.getMessage());
            model.addObject("entidade", professor);
            return model;
        }
    }
    @PostMapping("/atualizar")
    public ModelAndView salvar(@ModelAttribute("entidade")Professor professor){
        try {
            professoresService.salvar(professor);
            return new ModelAndView("redirect:/inicio/professores/"+professor.getId());
        }catch (Exception e){
            ModelAndView model = new ModelAndView("professores/editar-professor.html");
            model.addObject("erro",e.getMessage());
            model.addObject("entidade", professor);
            return model;
        }
    }

    @PostMapping("/{id}/excluir")
    public String excluir(@PathVariable("id") long id) {
        professoresService.excluirPeloId(id);
        return "redirect:/inicio/professores";
    }


}

