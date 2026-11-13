package br.com.nonnapizza.dao;

import br.com.nonnapizza.model.Cliente;
import br.com.nonnapizza.model.Pizzaiolo;
import br.com.nonnapizza.serlvet.config.ConnectionPoolConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PizzaioloDao {

    public boolean verificarCredenciaisAdm(Pizzaiolo pizzaiolo){
        String SQL = "SELECT * FROM PIZZAIOLO WHERE EMAIL = ?";

        try {
            Connection connection = ConnectionPoolConfig.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setString(1, pizzaiolo.getEmail());

            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("Email localizado com sucesso!");

            while (resultSet.next()) {

                String password = resultSet.getString("senha");

                if (password.equals(pizzaiolo.getSenha())) {
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

    public Pizzaiolo logarAdm(String email, String senha){
        String SQL = "SELECT * FROM PIZZAIOLO WHERE EMAIL = ?";

        try {
            Connection connection = ConnectionPoolConfig.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setString(1, email);

            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("Email localizado com sucesso!");

            while (resultSet.next()) {

                String password = resultSet.getString("senha");

                if (password.equals(senha)){
                    String emailNovo = (resultSet.getString("email"));

                    return new Pizzaiolo(emailNovo, senha);
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
