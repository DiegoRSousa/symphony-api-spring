package com.rs.symphony.repository;

import com.rs.symphony.model.Produto;
import com.rs.symphony.model.Status;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class ProdutoRepositoryTest {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private EntityManager em;

    @BeforeEach
    void setup() {
        cadastrarProduto();
    }

    @Test
    void ShouldReturnProdutosAtivados(){
        var produtosAtivos = produtoRepository.findAllByAtivado();

        assertThat(produtosAtivos).isNotEmpty();
        assertThat(Status.ATIVADO).isEqualTo(produtosAtivos.get(0).getStatus());

    }

    private void cadastrarProduto() {
        var produto = new Produto("0001", "Água Mineral", new BigDecimal("10.00"), Status.ATIVADO);
        em.persist(produto);
    }

}