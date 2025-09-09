package com.gabrielsmm.gmcontrol.dtos;

import com.gabrielsmm.gmcontrol.entities.enums.UsuarioStatus;
import lombok.Data;

import java.util.Set;

@Data
public class UsuarioResponseDTO {

    private Long id;
    private String nome;
    private String nomeUsuario;
    private String email;
    private UsuarioStatus status;
    private Set<PerfilDTO> perfis;
    private Set<Integer> usuarioModulos;

}
