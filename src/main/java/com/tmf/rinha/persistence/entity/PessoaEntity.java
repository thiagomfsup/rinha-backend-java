package com.tmf.rinha.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import java.util.UUID;

@Entity
@Table(name = "pessoas")
public class PessoaEntity {
    @Id
    private UUID id;
    private String nome;
    private String apelido;
    private String nascimento;
    private String stack;
    @Transient
    private String search;

    public UUID getId() {
        return id;
    }

    public PessoaEntity setId(UUID id) {
        this.id = id;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public PessoaEntity setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String getApelido() {
        return apelido;
    }

    public PessoaEntity setApelido(String apelido) {
        this.apelido = apelido;
        return this;
    }

    public String getNascimento() {
        return nascimento;
    }

    public PessoaEntity setNascimento(String nascimento) {
        this.nascimento = nascimento;
        return this;
    }

    public String getStack() {
        return stack;
    }

    public PessoaEntity setStack(String stack) {
        this.stack = stack;
        return this;
    }
}
