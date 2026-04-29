package br.imd.ufrn.enums;

public enum Shifts {
    MATUTINO("MAT"),
    VESPERTINO("VESP"),
    NOTURNO("NOT");

    private final String code;

    Shifts(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }
}
