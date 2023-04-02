package com.rs.symphony.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class Message {

    @Autowired
    private MessageSource messageSource;

    public String produtoNaoEncontrado(Object obj) {
        return getMessage("produto.naoEncontrado", obj);
    }

    public String adicionandoProduto(Object obj) {
        return getMessage("produto.adicionando", obj);
    }

    public String listandoProdutos(){
        return getMessage("produto.listando");
    }
    public String listandoProdutosAtivos() {
        return getMessage("produto.listando.ativos");
    }

    public String detalhandoProduto(Object obj) {
        return getMessage("produto.detalhando", obj);
    }

    public String atualizandoProduto(Object obj) {
        return getMessage("produto.atualizando", obj);
    }

    public String deletandoProduto(Object obj) {
        return getMessage("produto.deletando", obj);
    }

    public String adicionandoPedido(Object obj) {
        return getMessage("pedido.adicionando", obj);
    }

    public String produtoDesativado(){
        return getMessage("produto.desativado");
    }

    public String produtoDesativadoCampo(Object i) {
        return getMessage("produto.desativadoCampo", i);
    }

    public String getMessage(String code, Object arg) {
        var args = new Object[10];
        args[0] = arg;
        return messageSource.getMessage(code, args, Locale.getDefault());
    }

    public String getMessage(String code) {
        return messageSource.getMessage(code, null, Locale.getDefault());
    }


}
