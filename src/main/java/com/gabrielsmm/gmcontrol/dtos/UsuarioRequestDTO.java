package com.gabrielsmm.gmcontrol.dtos;

import lombok.Data;

import java.util.Set;

@Data
public class UsuarioRequestDTO {

    private String nome;
    private String usuario;
    private String email;
    private String senha;
    private Set<Integer> perfis;

}
