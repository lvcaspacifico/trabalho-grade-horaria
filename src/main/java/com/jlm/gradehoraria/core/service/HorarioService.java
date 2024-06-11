package com.jlm.gradehoraria.core.service;
import java.util.Objects;

import com.jlm.gradehoraria.core.entity.Horario;
import com.jlm.gradehoraria.core.repository.HorarioRepository;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.jlm.gradehoraria.core.repository.GradeHorariaRepository;

@Service
public class HorarioService {

    @Autowired
    HorarioRepository horarioRepository;

    @Autowired
    GradeHorariaRepository gradeHorariaRepository;

    public List<Horario> obterTodos() {
        return horarioRepository.findAll();
    }
    public Optional<Horario> obterPeloId(long id) {
        return horarioRepository.findById(id);
    }
  

    public void salvar(Horario horario) {
        if (Strings.isBlank(horario.getData())) {
            throw new RuntimeException("Favor informar a data");
        }
        if (Objects.isNull(horario.getTurma())) {
            throw new RuntimeException("Favor informar a turma");
        }
        if (Objects.isNull(horario.getDiaDaSemana())) {
            throw new RuntimeException("Favor informar dia da semana");
        }
        if (Objects.isNull(horario.getPeriodo())) {
            throw new RuntimeException("Favor informar o periodo");
        }
        if (Objects.isNull(horario.getDisciplina())) {
            throw new RuntimeException("Favor informar a disciplina");
        }
        if (Objects.isNull(horario.getProfessor())) {
            throw new RuntimeException("Favor informar o professor");
        }
        if (Objects.isNull(horario.getSala())) {
            throw new RuntimeException("Favor informar a sala");
        }
        horarioRepository.save(horario);
    }
    
    public void excluirPeloId(Long horarioId) {
        gradeHorariaRepository.dissociatePrimeiroHorario(horarioId);
        gradeHorariaRepository.dissociateSegundoHorario(horarioId);
        horarioRepository.deleteById(horarioId);
    }
}