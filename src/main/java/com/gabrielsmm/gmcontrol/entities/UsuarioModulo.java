package com.gabrielsmm.gmcontrol.entities;

import com.gabrielsmm.gmcontrol.entities.enums.Modulo;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(exclude = { "usuario" })
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Entity
@Table(name = "usuarios_modulos")
public class UsuarioModulo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(name = "modulo", nullable = false)
    private Integer modulo;

    public UsuarioModulo(Long id, Usuario usuario, Modulo modulo) {
        this.id = id;
        this.usuario = usuario;
        this.modulo = (modulo == null) ? null : modulo.getCodigo();
    }

}
