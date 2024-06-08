package com.gabrielsmm.gmcontrol.dtos;

import lombok.Data;

import java.util.Set;

@Data
public class UsuarioResponseDTO {

    private Long id;
    private String nome;
    private String usuario;
    private String email;
    private Set<Integer> perfis;

}
