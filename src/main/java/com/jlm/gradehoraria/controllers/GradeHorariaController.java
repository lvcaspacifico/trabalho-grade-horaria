package com.jlm.gradehoraria.controllers;

import com.jlm.gradehoraria.core.entity.*;
import com.jlm.gradehoraria.core.service.*;
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
@RequestMapping("/inicio/grades-horarias")
public class GradeHorariaController {

    @Autowired
    private HorarioService horarioService;
    @Autowired
    private GradeHorariaService gradeHorariaService;

    @GetMapping
    public ModelAndView index(){
        ModelAndView view = new ModelAndView("grades-horarias/grades-horarias.html");
        view.addObject("gradesHorarias", gradeHorariaService.obterTodos());
        return view;
    }

    @GetMapping("/{id}")
    public ModelAndView visualizar(@PathVariable("id") long id){
        ModelAndView view = new ModelAndView("grades-horarias/visualizar.html");
        var opt = gradeHorariaService.obterPeloId(id);
        opt.ifPresent(entidade -> view.addObject("entidade", entidade));
        return view;
    }

    @GetMapping("/{id}/editar-grade-horaria")
    public ModelAndView editar(@PathVariable("id") long id){
        ModelAndView view = new ModelAndView("grades-horarias/editar-grade-horaria.html");
        var opt = gradeHorariaService.obterPeloId(id);
        opt.ifPresent(entidade -> {
            view.addObject("entidade", entidade);
            view.addObject("horarios", horarioService.obterTodos());
        });
        return view;
    }

    @GetMapping("/nova-grade-horaria")
    public ModelAndView criarNovaGradeHoraria(){
        ModelAndView view = new ModelAndView("grades-horarias/nova-grade-horaria.html");
        view.addObject("entidade", new Horario());
        view.addObject("horarios", horarioService.obterTodos());
        return view;
    }

    @PostMapping("/nova-grade-horaria")
    public ModelAndView criarNovaGradeHoraria(@ModelAttribute("entidade") GradeHoraria gradeHoraria,
                                         @RequestParam("primeiroHorarioId") long primeiroHorarioId,
                                         @RequestParam("segundoHorarioId") long segundoHorarioId){
        try {
            var primeiroHorario = horarioService.obterPeloId(primeiroHorarioId).orElseThrow(() -> new RuntimeException("Horário não encontrado"));
            gradeHoraria.setPrimeiroHorario(primeiroHorario);
            var segundoHorario = horarioService.obterPeloId(segundoHorarioId).orElseThrow(() -> new RuntimeException("Horário não encontrado"));
            gradeHoraria.setSegundoHorario(segundoHorario);
            gradeHorariaService.salvar(gradeHoraria);
            return new ModelAndView("redirect:/inicio/grades-horarias/" + gradeHoraria.getId());
        } catch (Exception e) {
            ModelAndView model = new ModelAndView("grades-horarias/nova-grade-horaria.html");
            model.addObject("erro", e.getMessage());
            model.addObject("horarios", horarioService.obterTodos());
            return model;
        }
    }

    @PostMapping("/atualizar")
    public ModelAndView salvar(@ModelAttribute("entidade") GradeHoraria gradeHoraria,
                                @RequestParam("primeiroHorarioId") long primeiroHorarioId,
                                @RequestParam("segundoHorarioId") long segundoHorarioId){
        try {
            var primeiroHorario = horarioService.obterPeloId(primeiroHorarioId).orElseThrow(() -> new RuntimeException("Horário não encontrado"));
            gradeHoraria.setPrimeiroHorario(primeiroHorario);
            var segundoHorario = horarioService.obterPeloId(segundoHorarioId).orElseThrow(() -> new RuntimeException("Horário não encontrado"));
            gradeHoraria.setSegundoHorario(segundoHorario);
            gradeHorariaService.salvar(gradeHoraria);
            return new ModelAndView("redirect:/inicio/grades-horarias/" + gradeHoraria.getId());
        } catch (Exception e) {
            ModelAndView model = new ModelAndView("grades-horarias/editar-grade-horaria.html");
            model.addObject("erro", e.getMessage());
            model.addObject("horarios", horarioService.obterTodos());
            return model;
        }
    }
    @PostMapping("/{id}/excluir")
    public String excluir(@PathVariable("id") long id) {
        gradeHorariaService.excluirPeloId(id);
        return "redirect:/inicio/grades-horarias";
    }
}
