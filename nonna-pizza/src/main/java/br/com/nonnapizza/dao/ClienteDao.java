package br.com.nonnapizza.dao;

import br.com.nonnapizza.model.Cliente;
import br.com.nonnapizza.model.Endereco;
import br.com.nonnapizza.serlvet.config.ConnectionPoolConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

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


}
