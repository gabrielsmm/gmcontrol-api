package com.gabrielsmm.gmcontrol.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioIgrejaAcessoDTO {

    private IgrejaDTO igreja;
    private boolean possuiAcesso;

}
