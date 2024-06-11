package com.jlm.gradehoraria.controllers;

import com.jlm.gradehoraria.core.entity.Disciplina;
import com.jlm.gradehoraria.core.service.DisciplinaService;
import com.jlm.gradehoraria.core.service.ProfessoresService;
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
@RequestMapping("/inicio/disciplinas")
public class DisciplinaController {

    @Autowired
    private DisciplinaService disciplinaService;

    @Autowired
    private ProfessoresService professoresService;

    @GetMapping
    public ModelAndView index(){
        ModelAndView view = new ModelAndView("disciplinas/disciplinas.html");
        view.addObject("disciplinas", disciplinaService.obterTodos());
        return view;
    }

    @GetMapping("/{id}")
    public ModelAndView visualizar(@PathVariable("id") long id){
        ModelAndView view = new ModelAndView("disciplinas/visualizar.html");
        var opt = disciplinaService.obterPeloId(id);
        opt.ifPresent(entidade -> view.addObject("entidade", entidade));
        return view;
    }

    @GetMapping("/{id}/editar-disciplina")
    public ModelAndView editar(@PathVariable("id") long id){
        ModelAndView view = new ModelAndView("disciplinas/editar-disciplina.html");
        var opt = disciplinaService.obterPeloId(id);
        opt.ifPresent(entidade -> {
            view.addObject("entidade", entidade);
            view.addObject("professores", professoresService.obterTodos());
        });
        return view;
    }

    @GetMapping("/nova-disciplina")
    public ModelAndView criarNovaDisciplina(){
        ModelAndView view = new ModelAndView("disciplinas/nova-disciplina.html");
        view.addObject("entidade", new Disciplina());
        view.addObject("professores", professoresService.obterTodos());
        return view;
    }

    @PostMapping("/nova-disciplina")
    public ModelAndView criarNovaDisciplina(@ModelAttribute("entidade") Disciplina disciplina, @RequestParam("professorId") long professorId){
        try {
            var professor = professoresService.obterPeloId(professorId).orElseThrow(() -> new RuntimeException("Professor não encontrado"));
            disciplina.setProfessor(professor);
            disciplinaService.salvar(disciplina);
            return new ModelAndView("redirect:/inicio/disciplinas/" + disciplina.getId());
        }catch (Exception e){
            ModelAndView model = new ModelAndView("disciplinas/nova-disciplina.html");
            model.addObject("erro", e.getMessage());
            model.addObject("entidade", disciplina);
            model.addObject("professores", professoresService.obterTodos());
            return model;
        }
    }

    @PostMapping("/atualizar")
    public ModelAndView salvar(@ModelAttribute("entidade") Disciplina disciplina, @RequestParam("professorId") long professorId){
        try {
            var professor = professoresService.obterPeloId(professorId).orElseThrow(() -> new RuntimeException("Professor não encontrado"));
            disciplina.setProfessor(professor);
            disciplinaService.salvar(disciplina);
            return new ModelAndView("redirect:/inicio/disciplinas/" + disciplina.getId());
        }catch (Exception e){
            ModelAndView model = new ModelAndView("disciplinas/editar-disciplina.html");
            model.addObject("erro", e.getMessage());
            model.addObject("entidade", disciplina);
            model.addObject("professores", professoresService.obterTodos());
            return model;
        }
    }
    @PostMapping("/{id}/excluir")
    public String excluir(@PathVariable("id") long id) {
        disciplinaService.excluirPeloId(id);
        return "redirect:/inicio/disciplinas";
    }
}
