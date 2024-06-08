package com.gabrielsmm.gmcontrol.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gabrielsmm.gmcontrol.entities.enums.Perfil;
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

    private String nome;

    @Column(unique = true)
    private String usuario;

    @Column(unique = true)
    private String email;

    @JsonIgnore
    private String senha;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "PERFIS")
    private Set<Integer> perfis = new HashSet<>();

    public Usuario() {
        addPerfil(Perfil.USUARIO);
    }

    public Usuario(Long id, String nome, String usuario, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.usuario = usuario;
        this.email = email;
        this.senha = senha;
        addPerfil(Perfil.USUARIO);
    }

    public Set<Perfil> getPerfisEnum() {
        return perfis.stream().map(Perfil::toEnum).collect(Collectors.toSet());
    }

    public void addPerfil(Perfil perfil) {
        perfis.add(perfil.getCodigo());
    }

}
