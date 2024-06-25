package com.gabrielsmm.gmcontrol.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gabrielsmm.gmcontrol.entities.enums.UsuarioPerfil;
import com.gabrielsmm.gmcontrol.entities.enums.UsuarioStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Column(nullable = false)
    private Integer status = UsuarioStatus.ATIVO.getCodigo();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "PERFIS")
    private Set<Integer> perfis = new HashSet<>();

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UsuarioModulo> usuarioModulos = new HashSet<>();

    public Usuario() {
        addPerfil(UsuarioPerfil.USUARIO);
    }

    public Usuario(Long id, String nome, String nomeUsuario, String email, String senha, UsuarioStatus status) {
        this.id = id;
        this.nome = nome;
        this.nomeUsuario = nomeUsuario;
        this.email = email;
        this.senha = senha;
        this.status = (status == null) ? null : status.getCodigo();
        addPerfil(UsuarioPerfil.USUARIO);
    }

    public Set<UsuarioPerfil> getPerfisEnum() {
        return perfis.stream().map(UsuarioPerfil::toEnum).collect(Collectors.toSet());
    }

    public void addPerfil(UsuarioPerfil usuarioPerfil) {
        perfis.add(usuarioPerfil.getCodigo());
    }

}
