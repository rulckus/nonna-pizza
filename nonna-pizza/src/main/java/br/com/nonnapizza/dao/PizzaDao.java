package br.com.nonnapizza.dao;

import br.com.nonnapizza.model.Pizza;
import br.com.nonnapizza.serlvet.config.ConnectionPoolConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PizzaDao {

    public void inserirPizza(Pizza pizza){
        String SQL = "INSERT INTO PIZZAS(SABOR, TIPO, VALOR, DESCRICAO, DISPONIBILIDADE, FOTO) VALUES (?, ?, ?, ?, ?, ?);";

        try {
            Connection connection = ConnectionPoolConfig.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            System.out.println("Conectado com sucesso!");

            preparedStatement.setString(1, pizza.getSabor());
            preparedStatement.setString(2, pizza.getTipo());
            preparedStatement.setBigDecimal(3, pizza.getValor());
            preparedStatement.setString(4, pizza.getDescricao());
            preparedStatement.setInt(5, 1);
            preparedStatement.setString(6, pizza.getImage());


            preparedStatement.execute();

            System.out.println("Pizza criada com sucesso!");

            connection.close();
        }
        catch (Exception e){
            System.out.println("Erro ao inserir Pizza: " + e.getMessage());
        }
    }

    public List<Pizza> listarPizzas(){
        String SQL = "SELECT * FROM PIZZAS WHERE EXCLUIDA = FALSE";

        try {
            Connection connection = ConnectionPoolConfig.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            ResultSet resultSet = preparedStatement.executeQuery();

            List<Pizza> pizzas = new ArrayList<>();

            while (resultSet.next()) {

                Pizza pizza = new Pizza();

                pizza.setId(resultSet.getString("id_pizza"));
                pizza.setSabor(resultSet.getString("sabor"));
                pizza.setValor(resultSet.getBigDecimal("valor"));
                pizza.setDescricao(resultSet.getString("descricao"));
                pizza.setTipo(resultSet.getString("tipo"));
                pizza.setImage(resultSet.getString("foto"));
                pizza.setDisponibilidade(resultSet.getBoolean("disponibilidade"));

                pizzas.add(pizza);
            }

            System.out.println("Sucesso ao consultar Pizzas!");
            connection.close();

            return pizzas;
        }catch (Exception e){
            System.out.println("Erro ao listar pizzas: " + e.getMessage());
        }

        return Collections.emptyList();
    }

    public void deletePizzaById(String pizzaId) {

        String SQL = "UPDATE PIZZAS SET EXCLUIDA = TRUE, DISPONIBILIDADE = FALSE WHERE ID_PIZZA = ?";

        try {

            Connection connection = ConnectionPoolConfig.getConnection();

            System.out.println("success in database connection");

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, pizzaId);
            preparedStatement.execute();

            System.out.println("Pizza deletada com sucesso " + pizzaId);

            connection.close();

        } catch (Exception e) {

            System.out.println("Erro ao excluir pizza: " + e.getMessage());

        }

    }

    public List<Pizza> listarPizzasCardapio(){
        String SQL = "SELECT * FROM PIZZAS WHERE DISPONIBILIDADE = TRUE";

        try {
            Connection connection = ConnectionPoolConfig.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            ResultSet resultSet = preparedStatement.executeQuery();

            List<Pizza> pizzas = new ArrayList<>();

            while (resultSet.next()) {

                Pizza pizza = new Pizza();

                pizza.setId(resultSet.getString("id_pizza"));
                pizza.setSabor(resultSet.getString("sabor"));
                pizza.setValor(resultSet.getBigDecimal("valor"));
                pizza.setDescricao(resultSet.getString("descricao"));
                pizza.setImage(resultSet.getString("foto"));

                pizzas.add(pizza);
            }

            System.out.println("Sucesso ao consultar Pizzas!");
            connection.close();

            return pizzas;
        }catch (Exception e){
            System.out.println("Erro ao listar pizzas: " + e.getMessage());
        }

        return Collections.emptyList();
    }

    public void atualizarPizza(Pizza pizza){
        String SQL = "UPDATE PIZZAS SET SABOR = ?, TIPO = ?, DESCRICAO= ?, VALOR= ?, FOTO = COALESCE(?, FOTO) WHERE ID_PIZZA = ?";

        try {

            Connection connection = ConnectionPoolConfig.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setString(1, pizza.getSabor());
            preparedStatement.setString(2, pizza.getTipo());
            preparedStatement.setString(3, pizza.getDescricao());
            preparedStatement.setBigDecimal(4, pizza.getValor());
            preparedStatement.setString(5, pizza.getImage());

            preparedStatement.setString(6, pizza.getId());
            preparedStatement.execute();

            System.out.println("Sucesso ao atualizar Pizza");

            connection.close();

        } catch (Exception e) {

            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());

        }
    }

    public Pizza acharPizzaId(Long pizzaId) {

        String SQL = "SELECT * FROM PIZZAS WHERE ID_PIZZA = ?";

        try {

            Connection connection = ConnectionPoolConfig.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setLong(1, pizzaId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                return null;
            }

            Pizza pizza = new Pizza();
            pizza.setId(resultSet.getString("id_pizza"));
            pizza.setSabor(resultSet.getString("sabor"));
            pizza.setTipo(resultSet.getString("tipo"));
            pizza.setValor(resultSet.getBigDecimal("valor"));
            pizza.setDescricao(resultSet.getString("descricao"));


            System.out.println("Pizza encontrada com sucesso " + pizzaId);

            connection.close();
            return pizza;
        } catch (Exception e) {

            System.out.println("Falha ao tentar localizar Pizza: " + e.getMessage());

        }
        return null;
    }

    public void atualizarPizzaDisponibilidade(String id, boolean disponibilidade){
        String SQL = "UPDATE PIZZAS SET DISPONIBILIDADE = ? WHERE ID_PIZZA = ?";

        try {

            Connection connection = ConnectionPoolConfig.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setBoolean(1, disponibilidade);
            preparedStatement.setString(2, id);

            preparedStatement.execute();

            System.out.println("Sucesso ao atualizar Pizza");

            connection.close();

        } catch (Exception e) {

            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());

        }
    }

    public List<Pizza> listarPizzasLading(){
        String SQL = "SELECT * FROM PIZZAS WHERE DISPONIBILIDADE = TRUE LIMIT 3";

        try {
            Connection connection = ConnectionPoolConfig.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            ResultSet resultSet = preparedStatement.executeQuery();

            List<Pizza> pizzas = new ArrayList<>();

            while (resultSet.next()) {

                Pizza pizza = new Pizza();

                pizza.setId(resultSet.getString("id_pizza"));
                pizza.setSabor(resultSet.getString("sabor"));
                pizza.setValor(resultSet.getBigDecimal("valor"));
                pizza.setDescricao(resultSet.getString("descricao"));
                pizza.setImage(resultSet.getString("foto"));

                pizzas.add(pizza);
            }

            System.out.println("Sucesso ao consultar Pizzas!");
            connection.close();

            return pizzas;
        }catch (Exception e){
            System.out.println("Erro ao listar pizzas: " + e.getMessage());
        }

        return Collections.emptyList();
    }
}
