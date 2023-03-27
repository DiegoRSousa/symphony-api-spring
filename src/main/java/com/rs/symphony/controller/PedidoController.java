package com.rs.symphony.controller;

import com.rs.symphony.controller.validation.ProdutoAtivoValidator;
import com.rs.symphony.repository.PedidoRepository;
import com.rs.symphony.repository.ProdutoRepository;
import com.rs.symphony.request.PedidoRequest;
import com.rs.symphony.response.PedidoResponse;
import com.rs.symphony.util.Message;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@SecurityRequirement(name = "bearer-key")
@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private static final Logger logger = LoggerFactory.getLogger(PedidoController.class);
    private final Message message;
    private final ProdutoRepository produtoRepository;
    private final PedidoRepository pedidoRepository;

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.addValidators(new ProdutoAtivoValidator(produtoRepository, message));
    }

    public PedidoController(Message message, ProdutoRepository produtoRepository, PedidoRepository pedidoRepository) {
        this.message = message;
        this.produtoRepository = produtoRepository;
        this.pedidoRepository = pedidoRepository;

    }

    @PostMapping
    public ResponseEntity<PedidoResponse> adicionar(@RequestBody @Valid PedidoRequest request,
                                                    UriComponentsBuilder uriBuilder) {
        logger.info(message.adicionandoPedido(request));
        var pedido = request.toModel(produtoRepository);
        pedidoRepository.save(pedido);
        var uri = uriBuilder.path("/produtos/{id}").buildAndExpand(pedido.getId()).toUri();

        return ResponseEntity.created(uri).body(new PedidoResponse(pedido));
    }
}
