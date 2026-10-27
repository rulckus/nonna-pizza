package br.com.nonnapizza.dao;

import br.com.nonnapizza.model.Pizza;
import br.com.nonnapizza.model.Produtos;
import br.com.nonnapizza.serlvet.config.ConnectionPoolConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProdutosDao {

    public void inserirProduto(Produtos produtos){
        String SQL = "INSERT INTO PRODUTOS(NOME, TIPO, VALOR, DESCRICAO) VALUES (?, ?, ?, ?);";

        try {
            Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "sa");

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setString(1, produtos.getNome());
            preparedStatement.setString(2, produtos.getTipo());
            preparedStatement.setBigDecimal(3, produtos.getValor());
            preparedStatement.setString(4, produtos.getDescricao());

            preparedStatement.execute();

            System.out.println("Inserido com sucesso!");

            connection.close();

        }catch (Exception e){
            System.out.println("Erro ao inserir produto!" + e.getMessage());
        }
    }

    public List<Produtos> listarProdutos(){
        String SQL = "SELECT * FROM PRODUTOS";

        try {
            Connection connection = ConnectionPoolConfig.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            ResultSet resultSet = preparedStatement.executeQuery();

            List<Produtos> produtos = new ArrayList<>();

            while (resultSet.next()) {


                String nome = resultSet.getString("nome");
                String id = resultSet.getString("id_produto");

                Produtos produto = new Produtos(id, nome);


                produtos.add(produto);
            }

            System.out.println("Sucesso ao consultar Produtos!");
            connection.close();

            return produtos;
        }catch (Exception e){
            System.out.println("Erro ao listar pizzas: " + e.getMessage());
        }

        return Collections.emptyList();
    }

    public void deleteProdutosById(String produtoId) {

        String SQL = "DELETE PRODUTOS WHERE ID_PRODUTO = ?";

        try {

            Connection connection = ConnectionPoolConfig.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, produtoId);
            preparedStatement.execute();

            System.out.println("Produto deletada com sucesso " + produtoId);

            connection.close();

        } catch (Exception e) {

            System.out.println("fail in database connection");

        }

    }

    public Produtos acharProdutoId(Long produtoId) {

        String SQL = "SELECT * FROM PRODUTOS WHERE ID_PRODUTO = ?";

        try {

            Connection connection = ConnectionPoolConfig.getConnection();


            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setLong(1, produtoId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                return null;
            }

            Produtos produto = new Produtos();
            produto.setId(resultSet.getString("id_produto"));
            produto.setNome(resultSet.getString("nome"));
            produto.setTipo(resultSet.getString("tipo"));
            produto.setValor(resultSet.getBigDecimal("valor"));
            produto.setDescricao(resultSet.getString("descricao"));


            System.out.println("Produto encontrado com sucesso " + produtoId);

            connection.close();
            return produto;
        } catch (Exception e) {

            System.out.println("Falha ao achar o produto");

        }
        return null;
    }
}
