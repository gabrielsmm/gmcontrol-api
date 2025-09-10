package com.gabrielsmm.gmcontrol.dtos;

import com.gabrielsmm.gmcontrol.entities.Perfil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PerfilDTO {

    private Integer id;
    private String nome;

    public PerfilDTO(Perfil perfil) {
        this.id = perfil.getId();
        this.nome = perfil.getNome();
    }

}
