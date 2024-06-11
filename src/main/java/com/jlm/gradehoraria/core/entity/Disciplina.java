package com.jlm.gradehoraria.core.entity;

import com.jlm.gradehoraria.core.entity.Professor;

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
public class Disciplina {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String nome;

    @ManyToOne
    private Professor professor;
    public Disciplina(String nome, Professor professor){
        this.nome = nome;
        this.professor = professor;
    }
}
