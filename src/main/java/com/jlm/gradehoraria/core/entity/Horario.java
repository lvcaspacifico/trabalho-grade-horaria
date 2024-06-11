package com.jlm.gradehoraria.core.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class Horario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String data;

    @ManyToOne
    private Turma turma;

    @ManyToOne
    private DiaDaSemana diaDaSemana;

    @ManyToOne
    private Periodo periodo;

    @ManyToOne
    private Disciplina disciplina;

    @ManyToOne
    private Professor professor;

    @ManyToOne
    private Sala sala;

    public Horario(String data, Turma turma, DiaDaSemana diaDaSemana,
                   Periodo periodo, Disciplina disciplina,Professor professor,
                   Sala sala){
        this.data = data;
        this.turma = turma;
        this.diaDaSemana = diaDaSemana;
        this.periodo = periodo;
        this.disciplina = disciplina;
        this.professor = professor;
        this.sala = sala;
    }
}
