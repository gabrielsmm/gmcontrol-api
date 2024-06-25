package com.gabrielsmm.gmcontrol.entities.enums;

public enum Modulo {

    MEMBRESIA_CRISTA(1, "Membresia Cristã");

    private int codigo;
    private String descricao;

    private Modulo(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Modulo toEnum(Integer codigo) {
        if (codigo == null) {
            return null;
        }

        for (Modulo x : Modulo.values()) {
            if (codigo.equals(x.getCodigo())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Código inválido: " + codigo);
    }

}
