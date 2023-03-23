package com.rs.symphony.repository;

import com.rs.symphony.model.Produto;
import com.rs.symphony.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    List<Produto> findByIdAndStatusEquals(Long id, Status status);
}
