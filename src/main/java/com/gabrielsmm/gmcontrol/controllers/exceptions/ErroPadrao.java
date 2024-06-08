package com.gabrielsmm.gmcontrol.controllers.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErroPadrao {

    private Long horario;
    private Integer status;
    private String erro;
    private String mensagem;
    private String caminho;

}
