package com.jlm.gradehoraria.core.service;

import com.jlm.gradehoraria.core.entity.Curso;
import com.jlm.gradehoraria.core.repository.CursoRepository;
import com.jlm.gradehoraria.core.repository.TurmaRepository;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

    @Autowired
    CursoRepository cursoRepository;

    @Autowired
    private TurmaRepository turmaRepository;


    public List<Curso> obterTodos() {
        return cursoRepository.findAll();
    }
    public Optional<Curso> obterPeloId(long id) {
        return cursoRepository.findById(id);
    }

    public void salvar(Curso curso) {
        if(Strings.isBlank(curso.getNome())){
            throw new RuntimeException("Favor informar o nome");
        }
        cursoRepository.save(curso);
    }
    @Transactional
    public void excluirPeloId(long cursoId) {
        turmaRepository.dissociateTurmasFromCurso(cursoId);
        cursoRepository.deleteById(cursoId);
    }
}