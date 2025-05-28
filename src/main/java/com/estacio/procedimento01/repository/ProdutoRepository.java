package com.estacio.procedimento01.repository;

import com.estacio.procedimento01.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
