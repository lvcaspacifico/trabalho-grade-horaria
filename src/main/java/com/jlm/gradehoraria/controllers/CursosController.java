package com.jlm.gradehoraria.controllers;

import com.jlm.gradehoraria.core.entity.Curso;
import com.jlm.gradehoraria.core.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/inicio/cursos")
public class CursosController {

    @Autowired
    private CursoService cursoService;
    
    @GetMapping
    public ModelAndView index(){
        ModelAndView view = new ModelAndView("cursos/cursos.html");
        view.addObject("cursos", cursoService.obterTodos());
        return view;
    }
    @GetMapping("/{id}")
    public ModelAndView visualizar(@PathVariable("id") long id){
        ModelAndView view = new ModelAndView("cursos/visualizar.html");
        var opt = cursoService.obterPeloId(id);
        opt.ifPresent(entidade -> view.addObject("entidade", entidade));
        return view;
    }
    @GetMapping("/{id}/editar-curso")
    public ModelAndView editar(@PathVariable("id") long id){
        ModelAndView view = new ModelAndView("cursos/editar-curso.html");
        var opt = cursoService.obterPeloId(id);
        opt.ifPresent(entidade -> view.addObject("entidade", entidade));
        return view;
    }
    @GetMapping("/novo-curso") // ESSE É GET !!!!!
    public ModelAndView criarNovoCurso(){
        ModelAndView view = new ModelAndView("cursos/novo-curso.html");
        view.addObject("entidade", new Curso());
        return view;
    }

    @PostMapping("/novo-curso") // ESSE É POST !!!!!
    public ModelAndView criarNovoCurso(@ModelAttribute("entidade") Curso curso){
        try {
            System.out.println(curso);
            curso.setId(0);
            cursoService.salvar(curso);
            return new ModelAndView("redirect:/inicio/cursos/"+curso.getId());
        }catch (Exception e){
            ModelAndView model = new ModelAndView("cursos/novo-curso.html");
            model.addObject("erro",e.getMessage());
            model.addObject("entidade", curso);
            return model;
        }
    }
    @PostMapping("/atualizar")
    public ModelAndView salvar(@ModelAttribute("entidade")Curso curso){
        try {
            cursoService.salvar(curso);
            return new ModelAndView("redirect:/inicio/cursos/"+curso.getId());
        }catch (Exception e){
            ModelAndView model = new ModelAndView("cursos/editar-curso.html");
            model.addObject("erro",e.getMessage());
            model.addObject("entidade", curso);
            return model;
        }
    }

    @PostMapping("/{id}/excluir")
    public String excluir(@PathVariable("id") long id) {
        cursoService.excluirPeloId(id);
        return "redirect:/inicio/cursos";
    }

}
