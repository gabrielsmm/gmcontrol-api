package com.gabrielsmm.gmcontrol.entities;

import com.gabrielsmm.gmcontrol.entities.enums.Modulo;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@Table(name = "usuarios_modulos")
public class UsuarioModulo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(name = "modulo", nullable = false)
    private Integer modulo;

    @Column(name = "chave_agrupamento", nullable = false)
    private UUID chaveAgrupamento;

    public UsuarioModulo(Long id, Usuario usuario, Modulo modulo, UUID chaveAgrupamento) {
        this.id = id;
        this.usuario = usuario;
        this.modulo = (modulo == null) ? null : modulo.getCodigo();
        this.chaveAgrupamento = chaveAgrupamento;
    }

}
