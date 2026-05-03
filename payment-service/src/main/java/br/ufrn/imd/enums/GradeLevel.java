package br.ufrn.imd.enums;

import lombok.Getter;

@Getter
public enum GradeLevel {
    QUINTO_ANO("5º Ano Fundamental"),
    SEXTO_ANO("6º Ano Fundamental"),
    SETIMO_ANO("7º Ano Fundamental");

    private final String description;

    GradeLevel(String description) {
        this.description = description;
    }
}