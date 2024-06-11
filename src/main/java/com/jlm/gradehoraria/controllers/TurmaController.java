package com.jlm.gradehoraria.controllers;

import com.jlm.gradehoraria.core.entity.Turma;
import com.jlm.gradehoraria.core.service.TurmaService;
import com.jlm.gradehoraria.core.service.CursoService;
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
@RequestMapping("/inicio/turmas")
public class TurmaController {

    @Autowired
    private TurmaService turmaService;

    @Autowired
    private CursoService cursoService;

    @GetMapping
    public ModelAndView index(){
        ModelAndView view = new ModelAndView("turmas/turmas.html");
        view.addObject("turmas", turmaService.obterTodos());
        return view;
    }

    @GetMapping("/{id}")
    public ModelAndView visualizar(@PathVariable("id") long id){
        ModelAndView view = new ModelAndView("turmas/visualizar.html");
        var opt = turmaService.obterPeloId(id);
        opt.ifPresent(entidade -> view.addObject("entidade", entidade));
        return view;
    }




    @GetMapping("/{id}/editar-turma")
    public ModelAndView editar(@PathVariable("id") long id){
        ModelAndView view = new ModelAndView("turmas/editar-turma.html");
        var opt = turmaService.obterPeloId(id);
        opt.ifPresent(entidade -> {
            view.addObject("entidade", entidade);
            view.addObject("cursos", cursoService.obterTodos());
        });
        return view;
    }

    @GetMapping("/nova-turma")
    public ModelAndView criarNovaTurma(){
        ModelAndView view = new ModelAndView("turmas/nova-turma.html");
        view.addObject("entidade", new Turma());
        view.addObject("cursos", cursoService.obterTodos());
        return view;
    }

    @PostMapping("/nova-turma")
    public ModelAndView criarNovaTurma(@ModelAttribute("entidade") Turma turma, @RequestParam("cursoId") long cursoId){
        try {
            var curso = cursoService.obterPeloId(cursoId).orElseThrow(() -> new RuntimeException("Curso não encontrado"));
            turma.setCurso(curso);
            turmaService.salvar(turma);
            return new ModelAndView("redirect:/inicio/turmas/" + turma.getId());
        }catch (Exception e){
            ModelAndView model = new ModelAndView("turmas/nova-turma.html");
            model.addObject("erro", e.getMessage());
            model.addObject("entidade", turma);
            model.addObject("cursos", cursoService.obterTodos());
            return model;
        }
    }

    @PostMapping("/atualizar")
    public ModelAndView salvar(@ModelAttribute("entidade") Turma turma, @RequestParam("cursoId") long cursoId){
        try {
            var curso = cursoService.obterPeloId(cursoId).orElseThrow(() -> new RuntimeException("Curso não encontrado"));
            turma.setCurso(curso);
            turmaService.salvar(turma);
            return new ModelAndView("redirect:/inicio/turmas/" + turma.getId());
        }catch (Exception e){
            ModelAndView model = new ModelAndView("turmas/editar-turma.html");
            model.addObject("erro", e.getMessage());
            model.addObject("entidade", turma);
            model.addObject("cursos", cursoService.obterTodos());
            return model;
        }
    }
    @PostMapping("/{id}/excluir")
    public String excluir(@PathVariable("id") long id) {
        turmaService.excluirPeloId(id);
        return "redirect:/inicio/turmas";
    }
}
