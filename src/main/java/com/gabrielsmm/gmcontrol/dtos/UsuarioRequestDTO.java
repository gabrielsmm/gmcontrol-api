package com.gabrielsmm.gmcontrol.dtos;

import lombok.Data;

import java.util.Set;

@Data
public class UsuarioRequestDTO {

    private String nome;
    private String nomeUsuario;
    private String email;
    private String senha;
    private Integer status;
    private Set<Integer> perfis;

}
