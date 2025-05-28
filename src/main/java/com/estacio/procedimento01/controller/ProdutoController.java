package com.estacio.procedimento01.controller;

import com.estacio.procedimento01.dao.ProdutoDAO;
import com.estacio.procedimento01.model.Produto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/produto")
public class ProdutoController {

    private final ProdutoDAO produtoDAO = new ProdutoDAO();
    
 // Página de Cadastro
    @GetMapping("/dados")
    public String paginaCadastro(Model model) {
        model.addAttribute("produto", new Produto());
        model.addAttribute("acao", "incluir");
        return "produto-dados";
    }

    // Listar produtos
    @GetMapping("/listar")
    public String listarProdutos(Model model) {
        model.addAttribute("produtos", produtoDAO.listarProdutos());
        return "produto-lista";
    }

    // Formulário de inclusão
    @GetMapping("/form-incluir")
    public String formIncluir(Model model) {
        model.addAttribute("produto", new Produto());
        model.addAttribute("acao", "incluir");
        return "produto-dados";
    }

    // Formulário de alteração
    @GetMapping("/form-alterar/{id}")
    public String formAlterar(@PathVariable Long id, Model model) {
        Produto produto = produtoDAO.obterProdutoPorId(id);
        if (produto != null) {
            model.addAttribute("produto", produto);
            model.addAttribute("acao", "alterar");
            return "produto-dados";
        } else {
            return "redirect:/produto/listar";
        }
    }

    // Salvar produto (inclusão ou alteração)
    @PostMapping("/salvar")
    public String salvarProduto(@ModelAttribute Produto produto, @RequestParam String acao) {
        if ("incluir".equals(acao)) {
            produtoDAO.inserirProduto(produto);
        } else if ("alterar".equals(acao)) {
            produtoDAO.atualizarProduto(produto);
        }
        return "redirect:/produto/listar";
    }

    // Excluir produto
    @GetMapping("/excluir/{id}")
    public String excluirProduto(@PathVariable Long id) {
        produtoDAO.excluirProduto(id);
        return "redirect:/produto/listar";
    }
}