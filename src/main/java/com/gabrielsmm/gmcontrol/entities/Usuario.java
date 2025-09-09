package com.gabrielsmm.gmcontrol.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gabrielsmm.gmcontrol.entities.enums.UsuarioStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String nomeUsuario;

    @Column(nullable = false, unique = true)
    private String email;

    @JsonIgnore
    @Column(nullable = false)
    private String senha;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UsuarioStatus status = UsuarioStatus.ATIVO;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "usuarios_perfis",
        joinColumns = @JoinColumn(name = "usuario_id"),
        inverseJoinColumns = @JoinColumn(name = "perfil_id")
    )
    private Set<Perfil> perfis = new HashSet<>();

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<UsuarioModulo> usuarioModulos = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "usuarios_igrejas",
        joinColumns = @JoinColumn(name = "usuario_id"),
        inverseJoinColumns = @JoinColumn(name = "igreja_id")
    )
    private Set<Igreja> igrejas = new HashSet<>();

    public Usuario() {

    }

    public Usuario(Long id, String nome, String nomeUsuario, String email, String senha, UsuarioStatus status) {
        this.id = id;
        this.nome = nome;
        this.nomeUsuario = nomeUsuario;
        this.email = email;
        this.senha = senha;
        this.status = (status == null) ? UsuarioStatus.ATIVO : status;
    }

}
