package com.gabrielsmm.gmcontrol.entities.enums;

import lombok.Getter;

@Getter
public enum UsuarioStatus {

    ATIVO(1),
    INATIVO(2);

    private final int codigo;

    private UsuarioStatus(int codigo) {
        this.codigo = codigo;
    }

    public static UsuarioStatus fromCodigo(int codigo) {
        for (UsuarioStatus status : UsuarioStatus.values()) {
            if (status.getCodigo() == codigo) {
                return status;
            }
        }
        throw new IllegalArgumentException("Código inválido para Status: " + codigo);
    }

}
