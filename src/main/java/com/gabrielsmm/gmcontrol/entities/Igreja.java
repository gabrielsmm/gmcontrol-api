package com.gabrielsmm.gmcontrol.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "igrejas")
public class Igreja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false, length = 255)
    private String nome;

    @Column(length = 255)
    private String endereco;

    @Column(length = 100)
    private String cidade;

    @Column(length = 50)
    private String estado;

    @Column(length = 20)
    private String cep;

    @Column(length = 20)
    private String telefone;

    @Column(length = 100)
    private String email;

    @Column(columnDefinition = "DATE")
    private LocalDate dataFundacao;

    @Column(length = 255)
    private String representante;

    @Column(length = 255)
    private String site;

    @Column(columnDefinition = "TEXT")
    private String observacoes;

    @ManyToMany(mappedBy = "igrejas", fetch = FetchType.LAZY)
    private Set<Usuario> usuarios = new HashSet<>();

}
