package com.jlm.gradehoraria.core.service;

import com.jlm.gradehoraria.core.entity.Sala;
import com.jlm.gradehoraria.core.entity.Turma;
import com.jlm.gradehoraria.core.repository.SalaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalaService {

    @Autowired
    SalaRepository salaRepository;

    public List<Sala> obterTodos() {
        return salaRepository.findAll();
    }
    public Optional<Sala> obterPeloId(long id) {
        return salaRepository.findById(id);
    }
}