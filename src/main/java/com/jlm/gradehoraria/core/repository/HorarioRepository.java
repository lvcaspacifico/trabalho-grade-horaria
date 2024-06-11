package com.jlm.gradehoraria.core.repository;

import com.jlm.gradehoraria.core.entity.Horario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface HorarioRepository  extends JpaRepository<Horario, Long> {

    List<Horario> findAllByData(String data);

    @Modifying
    @Transactional
    @Query("UPDATE Horario h SET h.professor = null WHERE h.professor.id = :professorId")
    void dissociateHorarioFromProfessor(@Param("professorId") Long cursoId);

    @Modifying
    @Transactional
    @Query("UPDATE Horario h SET h.disciplina = null WHERE h.disciplina.id = :disciplinaId")
    void dissociateHorarioFromDisciplina(@Param("disciplinaId") Long cursoId);

    @Modifying
    @Transactional
    @Query("UPDATE Horario h SET h.turma = null WHERE h.turma.id = :turmaId")
    void dissociateHorarioFromTurma(@Param("turmaId") Long cursoId);
    
}
