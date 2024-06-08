package com.gabrielsmm.gmcontrol.controllers.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ErroValidacao extends ErroPadrao {

    private List<CampoMensagem> erros = new ArrayList<>();

    public ErroValidacao(Long horario, Integer status, String erro, String mensagem, String caminho) {
        super(horario, status, erro, mensagem, caminho);
    }

    public List<CampoMensagem> getErros() {
        return erros;
    }

    public void addErro(String nomeCampo, String mensagem) {
        erros.add(new CampoMensagem(nomeCampo, mensagem));
    }

}
