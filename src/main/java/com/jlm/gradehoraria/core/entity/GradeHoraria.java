package com.jlm.gradehoraria.core.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.slf4j.Logger;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class GradeHoraria {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    private Horario primeiroHorario;

    @ManyToOne
    private Horario segundoHorario;

    public GradeHoraria(Horario primeiroHorario, Horario segundoHorario) {
        this.primeiroHorario = primeiroHorario;
        this.segundoHorario = segundoHorario;
    }

    public void printarGradeHoraria(Logger log) {
        log.info("//////// GRADE HORARIA DE " + primeiroHorario.getData() + " ////////");
        log.info("CURSO: " + primeiroHorario.getTurma().getCurso().getNome());
        log.info("======== Horario: 01 ========");
        log.info("TURMA CÓDIGO: " + primeiroHorario.getTurma().getCodTurma());
        log.info("TURMA SEMESTRE: " + primeiroHorario.getTurma().getSemestre());
        log.info("DIA DA SEMANA: " + primeiroHorario.getDiaDaSemana().getNome());
        log.info("PERÍODO: " + primeiroHorario.getPeriodo().getDescricao());
        log.info("DISCIPLINA: " + primeiroHorario.getDisciplina().getNome());
        log.info("PROFESSOR: " + primeiroHorario.getProfessor().getNome());
        log.info("SALA: " + primeiroHorario.getSala().getCodigoDaSala());
        log.info("======== Horario: 02 ========");
        log.info("TURMA CÓDIGO: " + segundoHorario.getTurma().getCodTurma());
        log.info("TURMA SEMESTRE: " + segundoHorario.getTurma().getSemestre());
        log.info("DIA DA SEMANA: " + segundoHorario.getDiaDaSemana().getNome());
        log.info("PERÍODO: " + segundoHorario.getPeriodo().getDescricao());
        log.info("DISCIPLINA: " + segundoHorario.getDisciplina().getNome());
        log.info("PROFESSOR: " + segundoHorario.getProfessor().getNome());
        log.info("SALA: " + segundoHorario.getSala().getCodigoDaSala());
    }
}
