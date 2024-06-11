package com.jlm.gradehoraria.core.service;

import com.jlm.gradehoraria.core.repository.TurmaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jlm.gradehoraria.core.repository.SalaRepository;
import com.jlm.gradehoraria.core.repository.ProfessorRepository;
import com.jlm.gradehoraria.core.repository.DisciplinaRepository;

@Service
public class InicioService {

    @Autowired
    TurmaRepository turmaRepository;

    @Autowired
    SalaRepository salaRepository;

    @Autowired
    ProfessorRepository professorRepository;

    @Autowired
    DisciplinaRepository disciplinaRepository;

    public long obterTurmas(){
        long turmas = turmaRepository.count();
        return turmas;
    }

    public long obterSalas(){
        long salas = salaRepository.count();
        return salas;
    }

    public long obterProfessores(){
        long professores = professorRepository.count();
        return professores;
    }

    public long obterDisciplinas(){
        long disciplinas = disciplinaRepository.count();
        return disciplinas;
    }

}