package com.jlm.gradehoraria.core.repository;

import com.jlm.gradehoraria.core.entity.Sala;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalaRepository   extends JpaRepository<Sala, Long> {

    @Override
    long count();
}
