package com.rs.symphony.request;

import com.rs.symphony.model.Produto;
import com.rs.symphony.model.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record AtualizaProduoRequest (
        @NotNull
        Long id,
        @NotBlank @Size(max = 32) String nome,
        @PositiveOrZero BigDecimal preco,
        @NotNull Status status) {
        public Produto toModel() {
                return new Produto(nome, preco, status);
        }
}
