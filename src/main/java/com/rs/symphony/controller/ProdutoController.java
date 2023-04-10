package com.rs.symphony.controller;

import com.rs.symphony.model.Produto;
import com.rs.symphony.repository.ProdutoRepository;
import com.rs.symphony.request.AtualizaProduoRequest;
import com.rs.symphony.request.NovoProdutoRequest;
import com.rs.symphony.response.ProdutoResponse;
import com.rs.symphony.util.Message;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@SecurityRequirement(name = "bearer-key")
@RequestMapping("/produtos")
@RestController
public class ProdutoController {

    private static final Logger logger = LoggerFactory.getLogger(ProdutoController.class);
    private final ProdutoRepository produtoRepository;
    private final Message message;

    public ProdutoController(ProdutoRepository produtoRepository, Message message) {
        this.produtoRepository = produtoRepository;
        this.message = message;
    }

    @PostMapping
    public ResponseEntity<ProdutoResponse> adicionar(@RequestBody @Valid  NovoProdutoRequest request,
                                                     UriComponentsBuilder uriBuilder) {
        logger.info(message.adicionandoProduto(request));
        Produto produto = request.toModel();
        produto = produtoRepository.save(produto);
        var uri = uriBuilder.path("/produtos/{id}").buildAndExpand(produto.getId()).toUri();
        return ResponseEntity.created(uri).body(new ProdutoResponse(produto));
    }

    @GetMapping
    public Page<ProdutoResponse> listar(@PageableDefault(size = 10, page = 0, sort = {"nome"}) Pageable pageable) {
        logger.info(message.listandoProdutos());
        return produtoRepository.findAll(pageable).map(ProdutoResponse::new);
    }

    @GetMapping("/ativos")
    public List<Produto> listarAtivos() {
        logger.info(message.listandoProdutosAtivos());
        return produtoRepository.findAllByAtivado();
    }

    @GetMapping("/{id}")
    public ProdutoResponse detalhar(@PathVariable Long id) {
        logger.info(message.detalhandoProduto(id));
        var produto = buscarPorId(id);
        return new ProdutoResponse(produto);
    }

    @PutMapping
    @Transactional
    public ProdutoResponse atualizar(@RequestBody @Valid AtualizaProduoRequest request) {
        var id = request.id();
        logger.info(message.atualizandoProduto(id));
        var produto = buscarPorId(id);
        produto.atualizar(request.toModel());
        return new ProdutoResponse(produto);
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        logger.info(message.deletandoProduto(id));
        var produto = buscarPorId(id);
        produtoRepository.delete(produto);
        return ResponseEntity.noContent().build();
    }

    private Produto buscarPorId(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> {
                    logger.info(message.produtoNaoEncontrado(id));
                    return new ResponseStatusException(HttpStatus.NOT_FOUND,
                            message.produtoNaoEncontrado(id));
                });
    }



}
