package com.gabrielsmm.gmcontrol.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(exclude = { "usuario", "igreja" })
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Entity
@Table(name = "usuarios_igrejas")
public class UsuarioIgreja {

    @EmbeddedId
    private UsuarioIgrejaId id;

    @ManyToOne
    @MapsId("usuarioId")
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @MapsId("igrejaId")
    @JoinColumn(name = "igreja_id", nullable = false)
    private Igreja igreja;

    public UsuarioIgreja(Usuario usuario, Igreja igreja) {
        this.usuario = usuario;
        this.igreja = igreja;
        this.id = new UsuarioIgrejaId(usuario.getId(), igreja.getId());
    }

}
