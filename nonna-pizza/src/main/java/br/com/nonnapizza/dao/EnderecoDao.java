package br.com.nonnapizza.dao;

import br.com.nonnapizza.model.Endereco;
import br.com.nonnapizza.model.Produtos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EnderecoDao {

    public void criarEndereco(Endereco endereco) {
            String SQL = "INSERT INTO ENDERECO (RUA, NUMERO, COMPLEMENTO, CEP, BAIRRO, CIDADE) VALUES (?,?,?,?,?,?)";

            try {

                Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "sa");

                PreparedStatement preparedStatement = connection.prepareStatement(SQL);

                preparedStatement.setString(1, endereco.getRua());
                preparedStatement.setString(2, endereco.getNumero());
                preparedStatement.setString(3, endereco.getComplemento());
                preparedStatement.setString(4, endereco.getCep());
                preparedStatement.setString(5, endereco.getBairro());
                preparedStatement.setString(6, endereco.getCidade());
                preparedStatement.execute();

                System.out.println("Sucesso ao criar o endereço");

                connection.close();

            }catch (Exception e){
                System.out.println("Falha ao conectar no banco de dados" + e.getMessage());
            }
    }

    public List<Endereco> listarEnderecos(){
        String SQL = "SELECT * FROM ENDERECO";

        try {
            Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "sa");

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            ResultSet resultSet = preparedStatement.executeQuery();

            List<Endereco> enderecos = new ArrayList<>();

            while (resultSet.next()) {


                String rua = resultSet.getString("rua");
                String id = resultSet.getString("id_endereco");
                String complemento = resultSet.getString("complemento");
                String cep = resultSet.getString("cep");
                String bairro = resultSet.getString("bairro");
                String cidade = resultSet.getString("cidade");
                String numero = resultSet.getString("numero");


                Endereco endereco = new Endereco(id, rua, cep, cidade, numero, complemento, bairro);


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

            Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "sa");

            System.out.println("success in database connection");

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, id);
            preparedStatement.execute();

            System.out.println("Endereco deletado com sucesso " + id);

            connection.close();

        } catch (Exception e) {

            System.out.println("fail in database connection");

        }

    }
}
