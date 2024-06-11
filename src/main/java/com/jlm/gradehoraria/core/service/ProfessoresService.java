package com.jlm.gradehoraria.core.service;

import com.jlm.gradehoraria.core.entity.Professor;
import com.jlm.gradehoraria.core.repository.ProfessorRepository;
import com.jlm.gradehoraria.core.repository.DisciplinaRepository;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.jlm.gradehoraria.core.repository.HorarioRepository;

@Service
public class ProfessoresService {

    @Autowired
    ProfessorRepository professorRepository;

    @Autowired
    DisciplinaRepository disciplinaRepository;

    @Autowired
    HorarioRepository horarioRepository;

    public List<Professor> obterTodos() {
        return professorRepository.findAll();
    }

    public Optional<Professor> obterPeloId(long id) {
        return professorRepository.findById(id);
    }

    public void salvar(Professor professor) {
        if(Strings.isBlank(professor.getNome())){
            throw new RuntimeException("Favor informar o nome");
        }
        if(Strings.isBlank(professor.getSobrenome())){
            throw new RuntimeException("Favor informar o sobrenome");
        }
        if(Strings.isBlank(professor.getEmail())){
            throw new RuntimeException("Favor informar o email");
        }
        professorRepository.save(professor);
    }
    public void excluirPeloId(long professorId) {
        disciplinaRepository.dissociateDisciplinaFromProfessor(professorId);
        horarioRepository.dissociateHorarioFromProfessor(professorId);
        professorRepository.deleteById(professorId);
    }
}