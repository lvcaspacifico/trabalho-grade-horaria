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
@RequestMapping("/inicio/horarios")
public class HorarioController {

    @Autowired
    private HorarioService horarioService;
    @Autowired
    private TurmaService turmaService;
    @Autowired
    private DiaDaSemanaService diaDaSemanaService;
    @Autowired
    private PeriodoService periodoService;
    @Autowired
    private DisciplinaService disciplinaService;
    @Autowired
    private ProfessoresService professoresService;
    @Autowired
    private SalaService salaService;

    @GetMapping
    public ModelAndView index(){
        ModelAndView view = new ModelAndView("horarios/horarios.html");
        view.addObject("horarios", horarioService.obterTodos());
        return view;
    }

    @GetMapping("/{id}")
    public ModelAndView visualizar(@PathVariable("id") long id){
        ModelAndView view = new ModelAndView("horarios/visualizar.html");
        var opt = horarioService.obterPeloId(id);
        opt.ifPresent(entidade -> view.addObject("entidade", entidade));
        return view;
    }

    @GetMapping("/{id}/editar-horario")
    public ModelAndView editar(@PathVariable("id") long id){
        ModelAndView view = new ModelAndView("horarios/editar-horario.html");
        var opt = horarioService.obterPeloId(id);
        opt.ifPresent(entidade -> {
            view.addObject("entidade", entidade);
            view.addObject("turmas", turmaService.obterTodos());
            view.addObject("diasDaSemana", diaDaSemanaService.obterTodos());
            view.addObject("periodos", periodoService.obterTodos());
            view.addObject("disciplinas", disciplinaService.obterTodos());
            view.addObject("professores", professoresService.obterTodos());
            view.addObject("salas", salaService.obterTodos());
        });
        return view;
    }

    @GetMapping("/novo-horario")
    public ModelAndView criarNovoHorario(){
        ModelAndView view = new ModelAndView("horarios/novo-horario.html");
        view.addObject("entidade", new Horario());
        view.addObject("turmas", turmaService.obterTodos());
        view.addObject("diasDaSemana", diaDaSemanaService.obterTodos());
        view.addObject("periodos", periodoService.obterTodos());
        view.addObject("disciplinas", disciplinaService.obterTodos());
        view.addObject("professores", professoresService.obterTodos());
        view.addObject("salas", salaService.obterTodos());
        return view;
    }

    @PostMapping("/novo-horario")
    public ModelAndView criarNovoHorario(@ModelAttribute("entidade") Horario horario,
                                         @RequestParam("turmaId") long turmaId,
                                         @RequestParam("diaDaSemanaId") long diaDaSemanaId,
                                         @RequestParam("periodoId") long periodoId,
                                         @RequestParam("disciplinaId") long disciplinaId,
                                         @RequestParam("professorId") long professorId,
                                         @RequestParam("salaId") long salaId){
        try {
            var turma = turmaService.obterPeloId(turmaId).orElseThrow(() -> new RuntimeException("Turma não encontrada"));
            horario.setTurma(turma);
            var diaDaSemana = diaDaSemanaService.obterPeloId(diaDaSemanaId).orElseThrow(() -> new RuntimeException("Dia não encontrado"));
            horario.setDiaDaSemana(diaDaSemana);
            var periodo = periodoService.obterPeloId(periodoId).orElseThrow(() -> new RuntimeException("Periodo não encontrado"));
            horario.setPeriodo(periodo);
            var disciplina = disciplinaService.obterPeloId(disciplinaId).orElseThrow(() -> new RuntimeException("Disciplina não encontrada"));
            horario.setDisciplina(disciplina);
            var professor = professoresService.obterPeloId(professorId).orElseThrow(() -> new RuntimeException("Professor não encontrado"));
            horario.setProfessor(professor);  // Correção aqui
            var sala = salaService.obterPeloId(salaId).orElseThrow(() -> new RuntimeException("Sala não encontrada"));
            horario.setSala(sala);  // Correção aqui    
            horarioService.salvar(horario);
            return new ModelAndView("redirect:/inicio/horarios/" + horario.getId());
        } catch (Exception e) {
            ModelAndView model = new ModelAndView("horarios/novo-horario.html");
            model.addObject("erro", e.getMessage());
            model.addObject("entidade", horario);
            model.addObject("turmas", turmaService.obterTodos());
            model.addObject("diasDaSemana", diaDaSemanaService.obterTodos());
            model.addObject("periodos", periodoService.obterTodos());
            model.addObject("disciplinas", disciplinaService.obterTodos());
            model.addObject("professores", professoresService.obterTodos());
            model.addObject("salas", salaService.obterTodos());
            return model;
        }
    }

    @PostMapping("/atualizar")
    public ModelAndView salvar(@ModelAttribute("entidade") Horario horario,
                               @RequestParam("turmaId") long turmaId,
                               @RequestParam("diaDaSemanaId") long diaDaSemanaId,
                               @RequestParam("periodoId") long periodoId,
                               @RequestParam("disciplinaId") long disciplinaId,
                               @RequestParam("professorId") long professorId,
                               @RequestParam("salaId") long salaId){
        try {
            var turma = turmaService.obterPeloId(turmaId).orElseThrow(() -> new IllegalArgumentException("Turma inválida:" + turmaId));
            horario.setTurma(turma);
            var diaDaSemana = diaDaSemanaService.obterPeloId(diaDaSemanaId).orElseThrow(() -> new RuntimeException("Dia não encontrado"));
            horario.setDiaDaSemana(diaDaSemana);
            var periodo = periodoService.obterPeloId(periodoId).orElseThrow(() -> new RuntimeException("Periodo não encontrado"));
            horario.setPeriodo(periodo);
            var disciplina = disciplinaService.obterPeloId(disciplinaId).orElseThrow(() -> new RuntimeException("Disciplina não encontrada"));
            horario.setDisciplina(disciplina);
            var professor = professoresService.obterPeloId(professorId).orElseThrow(() -> new RuntimeException("Professor não encontrado"));
            horario.setProfessor(professor);  // Correção aqui
            var sala = salaService.obterPeloId(salaId).orElseThrow(() -> new RuntimeException("Sala não encontrada"));
            horario.setSala(sala);  // Correção aqui    
            horarioService.salvar(horario);
            return new ModelAndView("redirect:/inicio/horarios/" + horario.getId());
        } catch (Exception e) {
            ModelAndView model = new ModelAndView("horarios/editar-horario.html");
            model.addObject("erro", e.getMessage());
            model.addObject("entidade", horario);
            model.addObject("turmas", turmaService.obterTodos());
            model.addObject("diasDaSemana", diaDaSemanaService.obterTodos());
            model.addObject("periodos", periodoService.obterTodos());
            model.addObject("disciplinas", disciplinaService.obterTodos());
            model.addObject("professores", professoresService.obterTodos());
            model.addObject("salas", salaService.obterTodos());
            return model;
        }
    }

    @PostMapping("/{id}/excluir")
    public String excluir(@PathVariable("id") long id) {
        horarioService.excluirPeloId(id);
        return "redirect:/inicio/horarios";
    }
}
