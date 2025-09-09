package com.gabrielsmm.gmcontrol.entities.enums;

import lombok.Getter;

@Getter
public enum UsuarioPerfil {
    MASTER("ROLE_MASTER"),
    ADMIN("ROLE_ADMIN"),
    TESOUREIRO("ROLE_TESOUREIRO"),
    PASTOR("ROLE_PASTOR"),
    SECRETARIO("ROLE_SECRETARIO"),
    USUARIO("ROLE_USUARIO");

    private final String role;

    UsuarioPerfil(String role) {
        this.role = role;
    }
}