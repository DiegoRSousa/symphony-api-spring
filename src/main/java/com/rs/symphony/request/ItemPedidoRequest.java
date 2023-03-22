package com.rs.symphony.request;

import com.rs.symphony.model.ItemPedido;
import com.rs.symphony.repository.ProdutoRepository;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record ItemPedidoRequest(
        @NotNull Long produtoId,
        @NotNull @Positive double quantidade,
        @NotNull @PositiveOrZero BigDecimal preco,
        @NotNull @PositiveOrZero BigDecimal subTotal) {
    public ItemPedido toModel(ProdutoRepository produtoRepository) {
        var produto = produtoRepository.getReferenceById(produtoId);
        return new ItemPedido(produto, quantidade, preco, subTotal);
    }
}
