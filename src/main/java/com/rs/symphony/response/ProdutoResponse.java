package com.rs.symphony.response;

import com.rs.symphony.model.Produto;
import com.rs.symphony.model.Status;

import java.math.BigDecimal;

public record ProdutoResponse(Long id, String codigo, String nome, BigDecimal preco, Status status) {

    public ProdutoResponse(Produto produto) {
        this(produto.getId(), produto.getCodigo(), produto.getNome(), produto.getPreco(), produto.getStatus());

    }
}
