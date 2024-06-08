package com.gabrielsmm.gmcontrol.controllers.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CampoMensagem {

    private String nomeCampo;
    private String mensagem;

}
