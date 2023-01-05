package com.rs.symphony.request;

import com.rs.symphony.model.Produto;

import com.rs.symphony.model.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record NovoProdutoRequest(
        @NotBlank @Size(min = 4, max = 4) String codigo,
        @NotBlank @Size(max = 32) String nome,
        @PositiveOrZero BigDecimal preco,
        @NotNull Status status) {
    public Produto toModel() {
        return new Produto(codigo, nome, preco, status);
    }
}
