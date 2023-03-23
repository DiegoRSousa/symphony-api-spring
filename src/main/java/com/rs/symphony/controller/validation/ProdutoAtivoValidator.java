package com.rs.symphony.controller.validation;

import com.rs.symphony.model.Status;
import com.rs.symphony.repository.ProdutoRepository;
import com.rs.symphony.request.PedidoRequest;
import org.springframework.stereotype.Component;

@Component
public class ProdutoAtivoValidator implements PedidoValidator {
    private final ProdutoRepository repository;

    public ProdutoAtivoValidator(ProdutoRepository repository) {
        this.repository = repository;
    }

    @Override
    public void validate(PedidoRequest request) {
        request.itensPedido().forEach(i -> {
            var produtos = repository.findByIdAndStatusEquals(i.produtoId(), Status.DESATIVADO);
            if(!produtos.isEmpty())
                throw new RuntimeException("Produto desativado");
        });
    }
}
