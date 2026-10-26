package br.com.nonnapizza.dao;

import br.com.nonnapizza.model.Cliente;
import br.com.nonnapizza.model.Endereco;
import br.com.nonnapizza.serlvet.config.ConnectionPoolConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ClienteDao{

    public void inserirCliente(Cliente cliente){
        String SQL = "INSERT INTO CLIENTE(NOME, EMAIL, TELEFONE, SENHA) VALUES (?, ?, ?, ?);";

        try {
            Connection connection = ConnectionPoolConfig.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            System.out.println("Conectado com sucesso!");

            preparedStatement.setString(1, cliente.getNome());
            preparedStatement.setString(2, cliente.getEmail());
            preparedStatement.setString(3, cliente.getTelefone());
            preparedStatement.setString(4, cliente.getSenha());

            preparedStatement.execute();

            System.out.println("Cliente inserido com sucesso!");

            connection.close();

        } catch (Exception e){
            System.out.println("Erro ao inserir cliente" + e.getMessage());
        }
    }

    public boolean verificarCredenciais(Cliente cliente){
        String SQL = "SELECT * FROM CLIENTE WHERE EMAIL = ?";

        try {
            Connection connection = ConnectionPoolConfig.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setString(1, cliente.getEmail());

            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("Email localizado com sucesso!");

            while (resultSet.next()) {

                String password = resultSet.getString("senha");

                if (password.equals(cliente.getSenha())) {
                    return true;
                }
            }

            connection.close();
            return false;
        } catch (Exception e){
            System.out.println("Erro:" + e.getMessage());
            return false;
        }
    }

    public Cliente logar(String email, String senha){
        String SQL = "SELECT * FROM CLIENTE WHERE EMAIL = ?";

        try {
            Connection connection = ConnectionPoolConfig.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setString(1, email);

            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("Email localizado com sucesso!");

            while (resultSet.next()) {

                String password = resultSet.getString("senha");

                if (password.equals(senha)){

                    String id = (resultSet.getString("id_cliente"));
                    String emailNovo = (resultSet.getString("email"));

                    return new Cliente(id, emailNovo, senha);
                }
            }

            connection.close();
            return null;
        } catch (Exception e){
            System.out.println("Erro:" + e.getMessage());
            return null;
        }
    }


}
