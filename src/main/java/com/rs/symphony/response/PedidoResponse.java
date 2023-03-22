package com.rs.symphony.response;

import com.rs.symphony.model.Pedido;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record PedidoResponse(Long id, List<ItemPedidoResponse> itensPedido, BigDecimal total,
                             LocalDateTime data, LocalDateTime criadoEm, LocalDateTime atualizadoEm) {

    public PedidoResponse(Pedido pedido) {
        this(pedido.getId(), pedido.getItensPedido().stream().map(ItemPedidoResponse::new).toList(),
                pedido.getTotal(), pedido.getData(),
                pedido.getCriadoEm(), pedido.getAtualizadoEm());
    }


}
