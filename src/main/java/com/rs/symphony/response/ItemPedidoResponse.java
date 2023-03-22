package com.rs.symphony.response;

import com.rs.symphony.model.ItemPedido;

import java.math.BigDecimal;

public record ItemPedidoResponse(Long id, String codigo, String nome, double quantidade, BigDecimal preco,
                                 BigDecimal subTotal) {
    public ItemPedidoResponse(ItemPedido itemPedido) {
        this(itemPedido.getId(), itemPedido.getProduto().getCodigo(), itemPedido.getProduto().getNome(),
                itemPedido.getQuantidade(), itemPedido.getPreco(), itemPedido.getSubTotal());

    }
}
