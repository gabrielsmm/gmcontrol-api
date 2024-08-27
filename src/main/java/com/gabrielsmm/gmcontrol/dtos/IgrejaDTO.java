package com.gabrielsmm.gmcontrol.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class IgrejaDTO {

    private Long id;

    @NotBlank(message = "Informe o nome")
    @Size(max = 255, message = "O nome deve ter no máximo 255 caracteres")
    private String nome;

    @Size(max = 255, message = "O endereço deve ter no máximo 255 caracteres")
    private String endereco;

    @Size(max = 100, message = "A cidade deve ter no máximo 100 caracteres")
    private String cidade;

    @Size(max = 50, message = "O estado deve ter no máximo 50 caracteres")
    private String estado;

    @Size(max = 20, message = "O cep deve ter no máximo 20 caracteres")
    private String cep;

    @Size(max = 20, message = "O telefone deve ter no máximo 20 caracteres")
    private String telefone;

    @Size(max = 100, message = "O email deve ter no máximo 100 caracteres")
    private String email;

    private LocalDate dataFundacao;

    @Size(max = 255, message = "O representante deve ter no máximo 255 caracteres")
    private String representante;

    @Size(max = 255, message = "O site deve ter no máximo 255 caracteres")
    private String site;

    private String observacoes;

}
