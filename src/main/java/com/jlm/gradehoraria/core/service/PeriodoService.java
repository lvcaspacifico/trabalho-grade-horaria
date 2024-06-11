package com.jlm.gradehoraria.core.service;

import com.jlm.gradehoraria.core.entity.Periodo;
import com.jlm.gradehoraria.core.repository.PeriodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PeriodoService {

    @Autowired
    PeriodoRepository periodoRepository;

    public List<Periodo> obterTodos() {
        return periodoRepository.findAll();
    }
    public Optional<Periodo> obterPeloId(long id) {
        return periodoRepository.findById(id);
    }
}