package com.jlm.gradehoraria.core.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class Sala {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String codigoDaSala;
    public Sala(String codigoDaSala){
        this.codigoDaSala = codigoDaSala;
    }
}
