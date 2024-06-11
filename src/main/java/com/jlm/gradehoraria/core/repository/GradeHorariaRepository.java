package com.jlm.gradehoraria.core.repository;

import com.jlm.gradehoraria.core.entity.GradeHoraria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface GradeHorariaRepository extends JpaRepository<GradeHoraria, Long> {

    List<GradeHoraria> findAllByPrimeiroHorarioData(String data);

    List<GradeHoraria> findAllByPrimeiroHorarioTurmaCursoNome(String cursoNome);

    @Modifying
    @Transactional
    @Query("UPDATE GradeHoraria gh SET gh.primeiroHorario = null WHERE gh.primeiroHorario.id = :horarioId")
    void dissociatePrimeiroHorario(@Param("horarioId") Long horarioId);

    @Modifying
    @Transactional
    @Query("UPDATE GradeHoraria gh SET gh.segundoHorario = null WHERE gh.segundoHorario.id = :horarioId")
    void dissociateSegundoHorario(@Param("horarioId") Long horarioId);
}