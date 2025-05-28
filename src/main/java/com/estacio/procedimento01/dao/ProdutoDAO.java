package com.estacio.procedimento01.dao;

import com.estacio.procedimento01.model.Produto;
import com.estacio.procedimento01.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    // Listar todos os produtos
    public List<Produto> listarProdutos() {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM produtos";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Produto produto = new Produto();
                produto.setId(resultSet.getLong("id_produto"));
                produto.setNome(resultSet.getString("nome"));
                produto.setQuantidade(resultSet.getInt("quantidade"));
                produto.setPrecoVenda(resultSet.getDouble("preco_venda"));
                produtos.add(produto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produtos;
    }

    // Inserir um novo produto
    public void inserirProduto(Produto produto) {
        String sql = "INSERT INTO produtos (nome, quantidade, preco_venda) VALUES (?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, produto.getNome());
            statement.setInt(2, produto.getQuantidade());
            statement.setDouble(3, produto.getPrecoVenda());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Atualizar um produto existente
    public void atualizarProduto(Produto produto) {
        String sql = "UPDATE produtos SET nome = ?, quantidade = ?, preco_venda = ? WHERE id_produto = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, produto.getNome());
            statement.setInt(2, produto.getQuantidade());
            statement.setDouble(3, produto.getPrecoVenda());
            statement.setLong(4, produto.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Excluir um produto
    public void excluirProduto(Long id) {
        String sql = "DELETE FROM produtos WHERE id_produto = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Obter um produto por ID
    public Produto obterProdutoPorId(Long id) {
        String sql = "SELECT * FROM produtos WHERE id_produto = ?";
        Produto produto = null;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                produto = new Produto();
                produto.setId(resultSet.getLong("id_produto"));
                produto.setNome(resultSet.getString("nome"));
                produto.setQuantidade(resultSet.getInt("quantidade"));
                produto.setPrecoVenda(resultSet.getDouble("preco_venda"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produto;
    }
}
