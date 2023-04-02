package com.rs.symphony.repository;

import com.rs.symphony.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    @Query(""" 
            select p from Produto p where p.status = 'ATIVADO'
            """)
    public List<Produto> findAllByAtivado();
}
