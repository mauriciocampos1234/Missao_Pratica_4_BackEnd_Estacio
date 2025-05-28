package com.estacio.procedimento01.service;

import com.estacio.procedimento01.model.Produto;
import com.estacio.procedimento01.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    // Listar todos os produtos
    public List<Produto> listarProdutos() {
        return produtoRepository.findAll();
    }

    // Salvar um produto (inclusão ou atualização)
    public Produto salvarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    // Obter um produto por ID
    public Produto obterProdutoPorId(Long id) {
        return produtoRepository.findById(id).orElse(null);
    }

    // Excluir um produto
    public void excluirProduto(Long id) {
        produtoRepository.deleteById(id);
    }
}