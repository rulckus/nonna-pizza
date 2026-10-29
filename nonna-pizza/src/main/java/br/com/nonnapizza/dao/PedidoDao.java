package br.com.nonnapizza.dao;

import br.com.nonnapizza.model.*;
import br.com.nonnapizza.serlvet.config.ConnectionPoolConfig;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;

public class PedidoDao {

    public Long salvarPedido(Pedido pedido) throws SQLException {
        String sqlPedido = "INSERT INTO pedido (id_cliente, id_endereco, id_pagamento, status, valor_total) "
                + "VALUES (?, ?, ?, ?, ?)";

        String sqlItem = "INSERT INTO itens_pedido (id_pedido, id_pizza, id_produto, quantidade, preco_unitario) "
                + "VALUES (?, ?, ?, ?, ?)";

        try(Connection connection = ConnectionPoolConfig.getConnection();
            PreparedStatement psPedido = connection.prepareStatement(sqlPedido,  Statement.RETURN_GENERATED_KEYS);
            PreparedStatement psItem = connection.prepareStatement(sqlItem)) {

            try {
                psPedido.setLong(1, pedido.getIdCliente());
                psPedido.setLong(2, pedido.getIdEndereco());
                psPedido.setLong(3, pedido.getIdPagamento());
                psPedido.setString(4, pedido.getStatus());
                psPedido.setBigDecimal(5, pedido.getValor());
                psPedido.executeUpdate();

                ResultSet rs = psPedido.getGeneratedKeys();
                rs.next();
                Long idPedido = rs.getLong(1);

                for (ItemPedido item : pedido.getItens()) {
                    psItem.setLong(1, idPedido);
                    psItem.setObject(2, item.getIdPizza());
                    psItem.setObject(3, item.getIdProduto());
                    psItem.setInt(4, item.getQuantidade());
                    psItem.setBigDecimal(5, item.getPrecoUnitario());
                    psItem.addBatch();
                }
                psItem.executeBatch();

                connection.commit();
                return idPedido;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
            }
    }

    public List<Pedido> listarPedidos(String id){
        String SQL = "SELECT p.id_pedido,\n" +
                "       p.horario,\n" +
                "       p.status,\n" +
                "       p.valor_total,\n" +
                "       COALESCE(pz.sabor, pr.nome) AS nome_item,\n" +
                "       i.quantidade,\n" +
                "       i.preco_unitario\n" +
                "FROM pedido p\n" +
                "JOIN itens_pedido i       ON i.id_pedido  = p.id_pedido\n" +
                "LEFT JOIN pizzas pz       ON pz.id_pizza  = i.id_pizza\n" +
                "LEFT JOIN produtos pr     ON pr.id_produto = i.id_produto\n" +
                "WHERE p.id_cliente = ?\n" +
                "ORDER BY p.horario DESC, p.id_pedido;";

        try {
            Connection connection = ConnectionPoolConfig.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setString(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            List<Pedido> pedidos = new ArrayList<>();

            while (resultSet.next()) {
                Pedido pedido = new Pedido();

                pedido.setId(Long.valueOf(resultSet.getString("ID_Pedido")));
                pedido.setValor(resultSet.getBigDecimal("VALOR_TOTAL"));
                pedido.setData((resultSet.getTimestamp("Horario").toLocalDateTime()));
                pedido.setStatus(resultSet.getString("STATUS"));


                pedidos.add(pedido);
            }

            System.out.println("Sucesso ao consultar Histórico de pedidos!");
            connection.close();

            return pedidos;
        }catch (Exception e){
            System.out.println("Erro ao listar histórico: " + e.getMessage());
        }

        return Collections.emptyList();
    }

    public List<PedidosAdm> listarPedidosAdm(){
        String SQL = "SELECT p.id_pedido                                                    AS id,\n" +
                "       c.nome                                                         AS cliente,\n" +
                "       LISTAGG(i.quantidade || 'x ' || COALESCE(pz.sabor, pr.nome), ', ') AS produtos,\n" +
                "       p.valor_total                                                  AS total,\n" +
                "       p.horario                                                      AS hora,\n" +
                "       p.status                                                       AS status\n" +
                "FROM pedido p\n" +
                "JOIN cliente c            ON c.id_cliente  = p.id_cliente\n" +
                "JOIN itens_pedido i       ON i.id_pedido   = p.id_pedido\n" +
                "LEFT JOIN pizzas pz       ON pz.id_pizza   = i.id_pizza\n" +
                "LEFT JOIN produtos pr     ON pr.id_produto = i.id_produto\n" +
                "GROUP BY p.id_pedido, c.nome, p.valor_total, p.horario, p.status\n" +
                "ORDER BY p.horario DESC;";

        try {
            Connection connection = ConnectionPoolConfig.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            ResultSet resultSet = preparedStatement.executeQuery();

            List<PedidosAdm> pedidos = new ArrayList<>();

            while (resultSet.next()) {
                PedidosAdm pedido = new PedidosAdm();

                pedido.setId(resultSet.getString("ID"));
                pedido.setCliente(resultSet.getString("CLIENTE"));
                pedido.setHora((resultSet.getTimestamp("HORA").toLocalDateTime()));
                pedido.setProdutos(resultSet.getString("PRODUTOS"));
                pedido.setTotal(resultSet.getBigDecimal("TOTAL"));
                pedido.setStatus(resultSet.getString("STATUS"));


                pedidos.add(pedido);
            }

            System.out.println("Sucesso ao consultar Histórico de pedidos!");
            connection.close();

            return pedidos;
        }catch (Exception e){
            System.out.println("Erro ao listar histórico: " + e.getMessage());
        }

        return Collections.emptyList();
    }

    public void atualizarPedidoPreparando(String id){
        String SQL = "UPDATE PEDIDO SET STATUS = 'PREPARANDO' WHERE ID_PEDIDO = ?";

        try {

            Connection connection = ConnectionPoolConfig.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setString(1, id);

            preparedStatement.execute();

            System.out.println("Sucesso ao atualizar Pedido");

            connection.close();

        } catch (Exception e) {

            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());

        }
    }

    public void atualizarPedidoAssando(String id){
        String SQL = "UPDATE PEDIDO SET STATUS = 'ASSANDO' WHERE ID_PEDIDO = ?";

        try {

            Connection connection = ConnectionPoolConfig.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setString(1, id);

            preparedStatement.execute();

            System.out.println("Sucesso ao atualizar Pedido");

            connection.close();

        } catch (Exception e) {

            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());

        }
    }

    public void atualizarPedidoPronto(String id){
        String SQL = "UPDATE PEDIDO SET STATUS = 'PRONTO' WHERE ID_PEDIDO = ?";

        try {

            Connection connection = ConnectionPoolConfig.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setString(1, id);

            preparedStatement.execute();

            System.out.println("Sucesso ao atualizar Pedido");

            connection.close();

        } catch (Exception e) {

            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());

        }
    }

    public void atualizarPedidoFinalizado(String id){
        String SQL = "UPDATE PEDIDO SET STATUS = 'FINALIZADO' WHERE ID_PEDIDO = ?";

        try {

            Connection connection = ConnectionPoolConfig.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setString(1, id);

            preparedStatement.execute();

            System.out.println("Sucesso ao atualizar Pedido");

            connection.close();

        } catch (Exception e) {

            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());

        }
    }

    public List<PedidoCard> listarPedidosProducaoRecebidos(){
        String SQL = "SELECT p.id_pedido,\n" +
                "       p.horario,\n" +
                "       p.status,\n" +
                "       c.nome                          AS cliente,\n" +
                "       i.quantidade,\n" +
                "       COALESCE(pz.sabor, pr.nome)     AS nome_item\n" +
                "FROM pedido p\n" +
                "JOIN cliente c            ON c.id_cliente  = p.id_cliente\n" +
                "JOIN itens_pedido i       ON i.id_pedido   = p.id_pedido\n" +
                "LEFT JOIN pizzas pz       ON pz.id_pizza   = i.id_pizza\n" +
                "LEFT JOIN produtos pr     ON pr.id_produto = i.id_produto\n" +
                "WHERE p.status = 'RECEBIDO'\n" +
                "ORDER BY p.horario ASC, p.id_pedido;";

        try {
            Connection connection = ConnectionPoolConfig.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            ResultSet resultSet = preparedStatement.executeQuery();

            Map<String, PedidoCard> cards = new LinkedHashMap<>();   // mantém a ordem do ORDER BY

            while (resultSet.next()) {
                String id = resultSet.getString("id_pedido");

                PedidoCard card = cards.get(id);
                if (card == null) {
                    card = new PedidoCard();
                    card.setId(id);
                    card.setHora(resultSet.getObject("horario", LocalDateTime.class));
                    card.setStatus(resultSet.getString("status"));
                    card.setCliente(abreviarNome(resultSet.getString("cliente")));
                    cards.put(id, card);
                }


                card.getItens().add(resultSet.getInt("quantidade") + "× " + resultSet.getString("nome_item"));
            }

            return new ArrayList<>(cards.values());
        }catch (Exception e){
            System.out.println("Erro ao listar histórico: " + e.getMessage());
        }

        return Collections.emptyList();
    }

    public List<PedidoCard> listarPedidosProducaoPreparando(){
        String SQL = "SELECT p.id_pedido,\n" +
                "       p.horario,\n" +
                "       p.status,\n" +
                "       c.nome                          AS cliente,\n" +
                "       i.quantidade,\n" +
                "       COALESCE(pz.sabor, pr.nome)     AS nome_item\n" +
                "FROM pedido p\n" +
                "JOIN cliente c            ON c.id_cliente  = p.id_cliente\n" +
                "JOIN itens_pedido i       ON i.id_pedido   = p.id_pedido\n" +
                "LEFT JOIN pizzas pz       ON pz.id_pizza   = i.id_pizza\n" +
                "LEFT JOIN produtos pr     ON pr.id_produto = i.id_produto\n" +
                "WHERE p.status = 'PREPARANDO'\n" +
                "ORDER BY p.horario ASC, p.id_pedido;";

        try {
            Connection connection = ConnectionPoolConfig.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            ResultSet resultSet = preparedStatement.executeQuery();

            Map<String, PedidoCard> cards = new LinkedHashMap<>();   // mantém a ordem do ORDER BY

            while (resultSet.next()) {
                String id = resultSet.getString("id_pedido");

                PedidoCard card = cards.get(id);
                if (card == null) {
                    card = new PedidoCard();
                    card.setId(id);
                    card.setHora(resultSet.getObject("horario", LocalDateTime.class));
                    card.setStatus(resultSet.getString("status"));
                    card.setCliente(abreviarNome(resultSet.getString("cliente")));
                    cards.put(id, card);
                }


                card.getItens().add(resultSet.getInt("quantidade") + "× " + resultSet.getString("nome_item"));
            }



            return new ArrayList<>(cards.values());
        }catch (Exception e){
            System.out.println("Erro ao listar histórico: " + e.getMessage());
        }

        return Collections.emptyList();
    }

    public List<PedidoCard> listarPedidosProducaoAssando(){
        String SQL = "SELECT p.id_pedido,\n" +
                "       p.horario,\n" +
                "       p.status,\n" +
                "       c.nome                          AS cliente,\n" +
                "       i.quantidade,\n" +
                "       COALESCE(pz.sabor, pr.nome)     AS nome_item\n" +
                "FROM pedido p\n" +
                "JOIN cliente c            ON c.id_cliente  = p.id_cliente\n" +
                "JOIN itens_pedido i       ON i.id_pedido   = p.id_pedido\n" +
                "LEFT JOIN pizzas pz       ON pz.id_pizza   = i.id_pizza\n" +
                "LEFT JOIN produtos pr     ON pr.id_produto = i.id_produto\n" +
                "WHERE p.status = 'ASSANDO'\n" +
                "ORDER BY p.horario ASC, p.id_pedido;";

        try {
            Connection connection = ConnectionPoolConfig.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            ResultSet resultSet = preparedStatement.executeQuery();

            Map<String, PedidoCard> cards = new LinkedHashMap<>();   // mantém a ordem do ORDER BY

            while (resultSet.next()) {
                String id = resultSet.getString("id_pedido");

                PedidoCard card = cards.get(id);
                if (card == null) {
                    card = new PedidoCard();
                    card.setId(id);
                    card.setHora(resultSet.getObject("horario", LocalDateTime.class));
                    card.setStatus(resultSet.getString("status"));
                    card.setCliente(abreviarNome(resultSet.getString("cliente")));
                    cards.put(id, card);
                }



                card.getItens().add(resultSet.getInt("quantidade") + "× " + resultSet.getString("nome_item"));
            }

            return new ArrayList<>(cards.values());
        }catch (Exception e){
            System.out.println("Erro ao listar histórico: " + e.getMessage());
        }

        return Collections.emptyList();
    }

    public List<PedidoCard> listarPedidosProducaoPronto(){
        String SQL = "SELECT p.id_pedido,\n" +
                "       p.horario,\n" +
                "       p.status,\n" +
                "       c.nome                          AS cliente,\n" +
                "       i.quantidade,\n" +
                "       COALESCE(pz.sabor, pr.nome)     AS nome_item\n" +
                "FROM pedido p\n" +
                "JOIN cliente c            ON c.id_cliente  = p.id_cliente\n" +
                "JOIN itens_pedido i       ON i.id_pedido   = p.id_pedido\n" +
                "LEFT JOIN pizzas pz       ON pz.id_pizza   = i.id_pizza\n" +
                "LEFT JOIN produtos pr     ON pr.id_produto = i.id_produto\n" +
                "WHERE p.status = 'PRONTO'\n" +
                "ORDER BY p.horario ASC, p.id_pedido;";

        try {
            Connection connection = ConnectionPoolConfig.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            ResultSet resultSet = preparedStatement.executeQuery();

            Map<String, PedidoCard> cards = new LinkedHashMap<>();   // mantém a ordem do ORDER BY

            while (resultSet.next()) {
                String id = resultSet.getString("id_pedido");

                PedidoCard card = cards.get(id);
                if (card == null) {
                    card = new PedidoCard();
                    card.setId(id);
                    card.setHora(resultSet.getObject("horario", LocalDateTime.class));
                    card.setStatus(resultSet.getString("status"));
                    card.setCliente(abreviarNome(resultSet.getString("cliente")));
                    cards.put(id, card);
                }

                card.getItens().add(resultSet.getInt("quantidade") + "× " + resultSet.getString("nome_item"));
            }

            return new ArrayList<>(cards.values());
        }catch (Exception e){
            System.out.println("Erro ao listar histórico: " + e.getMessage());
        }

        return Collections.emptyList();
    }

    private String abreviarNome(String nomeCompleto) {
        String[] partes = nomeCompleto.trim().split("\\s+");
        if (partes.length == 1) return partes[0];
        return partes[0] + " " + partes[partes.length - 1].charAt(0) + ".";
    }

    public String verificarStatus(String id){
        String SQL = "SELECT STATUS FROM PEDIDO WHERE ID_PEDIDO = ?";

        try {
            Connection connection = ConnectionPoolConfig.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setString(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                return resultSet.getString("status");
            }

        }catch (Exception e){
            System.out.println("Erro ao verificar pedido: " + e.getMessage());
        }
        return "";
    }




}
