package com.gabrielsmm.gmcontrol.entities.enums;

import lombok.Getter;

@Getter
public enum UsuarioStatus {

    ATIVO("ATIVO"),
    INATIVO("INATIVO");

    private final String valor;

    UsuarioStatus(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

    public static UsuarioStatus fromValor(String valor) {
        for (UsuarioStatus status : UsuarioStatus.values()) {
            if (status.getValor().equalsIgnoreCase(valor)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Status inválido: " + valor);
    }

}
