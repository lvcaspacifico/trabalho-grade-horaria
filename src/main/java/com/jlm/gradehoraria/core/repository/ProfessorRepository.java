package com.jlm.gradehoraria.core.repository;

import com.jlm.gradehoraria.core.entity.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {

    @Override
    long count();
}
