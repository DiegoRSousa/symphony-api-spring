package com.rs.symphony.controller;

import com.rs.symphony.repository.PedidoRepository;
import com.rs.symphony.repository.ProdutoRepository;
import com.rs.symphony.request.ItemPedidoRequest;
import com.rs.symphony.request.PedidoRequest;
import com.rs.symphony.response.PedidoResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class PedidoControllerTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private JacksonTester<PedidoRequest> pedidoRequest;
    @Autowired
    private JacksonTester<PedidoResponse> pedidoResponse;

    @MockBean
    private ProdutoRepository produtoRepository;
    @MockBean
    private PedidoRepository pedidoRepository;

    @Test
    @DisplayName("Deveria devolver codigo http 400 quando informacoes invalidas")
    @WithMockUser
    void teste01() throws Exception {
        var response = mvc.perform(post("/pedidos"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria devolver codigo http 200 quando informacoes validas")
    @WithMockUser
    void teste02() throws Exception {
        var itensPedido = List.of(
                new ItemPedidoRequest(1L, 1, new BigDecimal("5.00"), new BigDecimal("5.00")),
                new ItemPedidoRequest(2L, 2, new BigDecimal("10.00"), new BigDecimal("20.00")));

        var pedido = new PedidoRequest(itensPedido, new BigDecimal("20.00"), LocalDateTime.now());



        var response = mvc.perform(post("/pedidos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(pedidoRequest.write(pedido).getJson()))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

}