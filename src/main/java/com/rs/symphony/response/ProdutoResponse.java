package com.rs.symphony.response;

import com.rs.symphony.model.Produto;
import com.rs.symphony.model.Status;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProdutoResponse(Long id, String codigo, String nome, BigDecimal preco,
                              Status status, LocalDateTime criadoEm, LocalDateTime atualizadoEm) {

    public ProdutoResponse(Produto produto) {
        this(produto.getId(), produto.getCodigo(), produto.getNome(), produto.getPreco(),
                produto.getStatus(), produto.getCriadoEm(), produto.getAtualizadoEm());

    }
}
