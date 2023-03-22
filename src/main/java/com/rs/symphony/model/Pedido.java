package com.rs.symphony.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "pedido_id")
    private List<ItemPedido> itensPedido;
    @PositiveOrZero
    private BigDecimal total;
    private LocalDateTime data;
    private LocalDateTime criadoEm = LocalDateTime.now();
    private LocalDateTime atualizadoEm;

    public Pedido() { }

    public Pedido(List<ItemPedido> itensPedido, BigDecimal total, LocalDateTime data) {
        this.itensPedido = itensPedido;
        this.total = total;
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public List<ItemPedido> getItensPedido() {
        return itensPedido;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public LocalDateTime getData() {
        return data;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public LocalDateTime getAtualizadoEm() {
        return atualizadoEm;
    }
}
