package com.gabrielsmm.gmcontrol.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UsuarioModuloAcessoDTO {

    private int codigo;
    private String descricao;
    private boolean possuiAcesso;

}
