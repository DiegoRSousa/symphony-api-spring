package com.rs.symphony.controller;

import com.rs.symphony.model.Produto;
import com.rs.symphony.model.Status;
import com.rs.symphony.repository.ProdutoRepository;
import com.rs.symphony.request.NovoProdutoRequest;
import com.rs.symphony.response.ProdutoResponse;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class ProdutoControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProdutoRepository produtoRepository;

    @Autowired
    private JacksonTester<NovoProdutoRequest> novoProdutoRequest;
    @Autowired
    private JacksonTester<ProdutoResponse> produtoResponse;


    @Test
    @DisplayName("Deveria devolver codigo http 400 quando informacoes invalidas")
    @WithMockUser
    void test01() throws Exception {
        var response = mvc.perform(post("/produtos"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria devolver codigo http 201 quando informacoes validas")
    @WithMockUser
    void test02() throws Exception {
        var criadoEm = LocalDateTime.now();
        var novoProduto = new NovoProdutoRequest("0001", "Agua mineral", new BigDecimal("2.00"), Status.ATIVADO);
        var produto = new Produto("0001", "Agua mineral", new BigDecimal("2.00"), Status.ATIVADO);
        FieldUtils.writeField(produto, "criadoEm", criadoEm, true);


        when(produtoRepository.save(Mockito.any(Produto.class))).thenReturn(produto);

        var response = mvc.perform(post("/produtos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(novoProdutoRequest.write(novoProduto).getJson()))
                .andReturn().getResponse();


        var produtoEsperado = produtoResponse.write(new ProdutoResponse(null, "0001", "Agua mineral",
                new BigDecimal("2.00"), Status.ATIVADO, criadoEm, null)).getJson();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getContentAsString()).isEqualTo(produtoEsperado);

    }

}