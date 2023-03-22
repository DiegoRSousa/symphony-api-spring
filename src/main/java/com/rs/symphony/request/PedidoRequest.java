package com.rs.symphony.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rs.symphony.model.Pedido;
import com.rs.symphony.repository.ProdutoRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record PedidoRequest(
        @NotNull @Valid List<ItemPedidoRequest> itensPedido,
        @PositiveOrZero BigDecimal total,
        @NotNull @JsonFormat(pattern = "dd/MM/yyyy HH:mm") LocalDateTime data) {
    public Pedido toModel(ProdutoRepository produtoRepository) {
        var itensPedido = itensPedido().stream().map(i -> i.toModel(produtoRepository)).toList();
        return new Pedido(itensPedido, total, data);
    }
}
