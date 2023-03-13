package com.rs.symphony.controller;

import com.rs.symphony.dto.DadosAutenticacao;
import com.rs.symphony.dto.TokenJWT;
import com.rs.symphony.model.Usuario;
import com.rs.symphony.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/token")
public class AutenticacaoController {

    private AuthenticationManager manager;
    private TokenService tokenService;

    public AutenticacaoController(AuthenticationManager manager, TokenService tokenService) {
        this.manager = manager;
        this.tokenService = tokenService;
    }

    @PostMapping
    public TokenJWT getToken(@RequestBody @Valid DadosAutenticacao dadosAutenticacao) {
        var authToken = new UsernamePasswordAuthenticationToken(dadosAutenticacao.login(), dadosAutenticacao.senha());
        var tokenJWT = tokenService.gerarToken((Usuario) manager.authenticate(authToken).getPrincipal());
        return new TokenJWT(tokenJWT);
    }
}
