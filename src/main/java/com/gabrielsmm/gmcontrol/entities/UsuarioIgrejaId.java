package com.gabrielsmm.gmcontrol.entities;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class UsuarioIgrejaId implements Serializable {

    private Long usuarioId;
    private Long igrejaId;

}
