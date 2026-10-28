package br.com.nonnapizza.dao;

import br.com.nonnapizza.model.Cliente;
import br.com.nonnapizza.model.Endereco;
import br.com.nonnapizza.model.Produtos;
import br.com.nonnapizza.serlvet.config.ConnectionPoolConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EnderecoDao {

    public void criarEndereco(Endereco endereco, Cliente cliente) {
            String SQL = "INSERT INTO ENDERECO (RUA, NUMERO, COMPLEMENTO, CEP, BAIRRO, CIDADE, ID_CLIENTE) VALUES (?,?,?,?,?,?,?)";

            try {

                Connection connection = ConnectionPoolConfig.getConnection();

                PreparedStatement preparedStatement = connection.prepareStatement(SQL);

                preparedStatement.setString(1, endereco.getRua());
                preparedStatement.setString(2, endereco.getNumero());
                preparedStatement.setString(3, endereco.getComplemento());
                preparedStatement.setString(4, endereco.getCep());
                preparedStatement.setString(5, endereco.getBairro());
                preparedStatement.setString(6, endereco.getCidade());
                preparedStatement.setString(7, cliente.getId());

                preparedStatement.execute();

                System.out.println("Sucesso ao criar o endereço");

                connection.close();

            }catch (Exception e){
                System.out.println("Falha ao conectar no banco de dados" + e.getMessage());
            }
    }

    public List<Endereco> listarEnderecos(String id){
        String SQL = "SELECT * FROM ENDERECO WHERE ID_CLIENTE = ?";

        try {
            Connection connection = ConnectionPoolConfig.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setString(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            List<Endereco> enderecos = new ArrayList<>();

            while (resultSet.next()) {
                Endereco endereco = new Endereco();

                endereco.setId(resultSet.getString("ID_ENDERECO"));
                endereco.setRua(resultSet.getString("rua"));
                endereco.setComplemento(resultSet.getString("complemento"));
                endereco.setCep(resultSet.getString("cep"));
                endereco.setBairro(resultSet.getString("bairro"));
                endereco.setCidade(resultSet.getString("cidade"));
                endereco.setNumero(resultSet.getString("numero"));


                enderecos.add(endereco);
            }

            System.out.println("Sucesso ao consultar Enderecos!");
            connection.close();

            return enderecos;
        }catch (Exception e){
            System.out.println("Erro ao listar pizzas: " + e.getMessage());
        }

        return Collections.emptyList();
    }

    public void deleteEnderecoById(String id) {

        String SQL = "DELETE ENDERECO WHERE ID_ENDERECO = ?";

        try {

            Connection connection = ConnectionPoolConfig.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, id);
            preparedStatement.execute();

            System.out.println("Endereco deletado com sucesso " + id);

            connection.close();

        } catch (Exception e) {

            System.out.println("fail in database connection");

        }

    }

    public void atualizarEndereco(Endereco endereco) {

        String SQL = "UPDATE ENDERECO SET RUA = ?, NUMERO = ?, CIDADE = ?, COMPLEMENTO = ?, BAIRRO = ?  WHERE ID_ENDERECO = ?";

        try {

            Connection connection = ConnectionPoolConfig.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setString(1, endereco.getRua());
            preparedStatement.setString(2, endereco.getNumero());
            preparedStatement.setString(3, endereco.getCidade());
            preparedStatement.setString(4, endereco.getComplemento());
            preparedStatement.setString(5, endereco.getBairro());
            preparedStatement.setString(6, endereco.getId());
            preparedStatement.execute();

            System.out.println("Sucesso ao atualizar Endereco");

            connection.close();

        } catch (Exception e) {

            System.out.println("Falha ao atualizar Endereco");
            System.out.println("Error: " + e.getMessage());

        }

    }
}
