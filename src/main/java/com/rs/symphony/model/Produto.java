package com.rs.symphony.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "produtos")
public class Produto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size(min = 4, max = 4)
    private String codigo;
    @NotBlank
    @Size(max = 32)
    private String nome;
    @PositiveOrZero
    private BigDecimal preco;
    @Enumerated(EnumType.STRING)
    @NotNull
    private Status status;
    private LocalDateTime criadoEm = LocalDateTime.now();
    private LocalDateTime atualizadoEm;

    public Produto() {}

    public Produto(@NotBlank String codigo, @NotBlank String nome, @PositiveOrZero BigDecimal preco,
                   @NotNull Status status) {
        this.codigo = codigo;
        this.nome = nome;
        this.preco = preco;
        this.status = status;
    }

    public Produto(String nome, BigDecimal preco, Status status) {
        this.nome = nome;
        this.preco = preco;
        this.status = status;
    }

    public void atualizar(Produto produto) {
        this.nome = produto.getNome();
        this.preco = produto.getPreco();
        this.status = produto.getStatus();
        this.atualizadoEm = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public Status getStatus() {
        return status;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public LocalDateTime getAtualizadoEm() {
        return atualizadoEm;
    }
}