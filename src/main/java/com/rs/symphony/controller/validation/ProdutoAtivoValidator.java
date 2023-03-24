package com.rs.symphony.controller.validation;

import com.rs.symphony.repository.ProdutoRepository;
import com.rs.symphony.request.PedidoRequest;
import com.rs.symphony.util.Message;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.stream.IntStream;

public class ProdutoAtivoValidator implements Validator {
    private final ProdutoRepository repository;
    private final Message message;

    public ProdutoAtivoValidator(ProdutoRepository repository, Message message) {
        this.repository = repository;
        this.message = message;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return PedidoRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        var request = (PedidoRequest) target;
        var itens = request.itensPedido();

        IntStream.range(0, itens.size()).forEach(i -> {
            var produto = repository.findById(itens.get(i).produtoId());
            if(produto.isPresent() && !produto.get().isAtivo())
                errors.rejectValue(message.getProdutoDesativadoCampo(i), null,
                        message.produtoDesativado());
        });
    }
}
