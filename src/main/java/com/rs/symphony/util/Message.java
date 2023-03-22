package com.rs.symphony.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class Message {

    public static final String PRODUTO_NAO_ENCONTRADO = "produto.naoEncontrado";
    public static final String ADICIONANDO_PRODUTO = "produto.adicionando";
    public static final String LISTANDO_PRODUTOS = "produto.listando";
    public static final String DETALHANDO_PRODUTO = "produto.detalhando";
    public static final String ATUALIZANDO_PRODUTO = "produto.atualizando";
    public static final String DELETANDO_PRODUTO = "produto.deletando";
    public static final String ADICIONANDO_PEDIDO = "pedido.adicionando";

    @Autowired
    private MessageSource messageSource;

    public String getMessage(String code, Object arg) {
        var args = new Object[10];
        args[0] = arg;
        return messageSource.getMessage(code, args, Locale.getDefault());
    }

    public String getMessage(String code) {
        return messageSource.getMessage(code, null, Locale.getDefault());
    }

}
