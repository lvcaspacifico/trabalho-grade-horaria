package com.jlm.gradehoraria.core.service;

import com.jlm.gradehoraria.core.entity.Disciplina;
import com.jlm.gradehoraria.core.repository.DisciplinaRepository;
import com.jlm.gradehoraria.core.repository.HorarioRepository;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;



@Service
public class DisciplinaService {

    @Autowired
    DisciplinaRepository disciplinaRepository;

    @Autowired
    HorarioRepository  horarioRepository;

    public List<Disciplina> obterTodos() {
        return disciplinaRepository.findAll();
    }
    public Optional<Disciplina> obterPeloId(long id) {
        return disciplinaRepository.findById(id);
    }

    public void salvar(Disciplina disciplina) {
        if (Strings.isBlank(disciplina.getNome())) {
            throw new RuntimeException("Favor informar o nome");
        }
        if (disciplina.getProfessor() == null) {
            throw new RuntimeException("Favor informar o professor");
        }
        disciplinaRepository.save(disciplina);
    }
    
    public void excluirPeloId(long id) {
        horarioRepository.dissociateHorarioFromDisciplina(id);
        disciplinaRepository.deleteById(id);
    }
}