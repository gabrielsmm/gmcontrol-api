package com.gabrielsmm.gmcontrol.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UsuarioPerfilAcessoDTO {

    private Integer id;
    private String nome;
    private String descricao;
    private boolean possuiAcesso;

}
