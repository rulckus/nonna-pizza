package br.com.nonnapizza.dao;

import br.com.nonnapizza.model.Endereco;
import br.com.nonnapizza.model.ItemPedido;
import br.com.nonnapizza.model.Pedido;
import br.com.nonnapizza.serlvet.config.ConnectionPoolConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
}
