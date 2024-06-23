package com.gabrielsmm.gmcontrol.dtos;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.Set;

@Data
public class UsuarioUpdateRequestDTO {

    @NotBlank(message = "Informe o nome")
    private String nome;

    @NotBlank(message = "Informe o nome de usuário")
    private String nomeUsuario;

    @NotBlank(message = "Informe o e-mail")
    @Email(message = "Informe um e-mail válido")
    private String email;

    @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
    private String senha;

    @NotNull(message = "Informe o status do usuário")
    private Integer status;

    @NotEmpty(message = "Informe pelo menos um perfil")
    private Set<Integer> perfis;

}
