package com.rs.symphony.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

@Entity
@Table(name = "itens_pedido")
public class ItemPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Produto produto;
    @NotNull
    @Positive
    private double quantidade;
    @NotNull
    @PositiveOrZero
    private BigDecimal preco;
    @NotNull
    @PositiveOrZero
    private BigDecimal subTotal;

    public ItemPedido() { }

    public ItemPedido(Produto produto, double quantidade, BigDecimal preco, BigDecimal subTotal) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.preco = preco;
        this.subTotal = subTotal;
    }

    public Long getId() {
        return id;
    }

    public Produto getProduto() {
        return produto;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }
}
