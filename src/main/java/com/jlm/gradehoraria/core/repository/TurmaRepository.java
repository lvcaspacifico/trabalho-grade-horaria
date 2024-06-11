package com.jlm.gradehoraria.core.repository;

import com.jlm.gradehoraria.core.entity.Turma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface TurmaRepository extends JpaRepository<Turma, Long> {
    @Modifying
    @Transactional
    @Query("UPDATE Turma t SET t.curso = null WHERE t.curso.id = :cursoId")
    void dissociateTurmasFromCurso(@Param("cursoId") Long cursoId);
}
