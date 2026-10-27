package br.com.nonnapizza.serlvet;

import br.com.nonnapizza.dao.PedidoDao;
import br.com.nonnapizza.model.*;
import org.apache.commons.logging.Log;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;

@WebServlet("/pedido")
public class CreatePedidoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        Cliente cliente = (Cliente) session.getAttribute("loggeduser");
        Carrinho carrinho = (Carrinho) session.getAttribute("carrinho");

        if (cliente == null) { resp.sendRedirect("login.html"); return; }
        if (carrinho == null || carrinho.isVazio()) { resp.sendRedirect("carrinho"); return; }

        String idEndereco = req.getParameter("endereco");

        Pedido pedido = new Pedido();
        pedido.setIdCliente(Long.parseLong(cliente.getId()));
        pedido.setIdEndereco(Long.parseLong(idEndereco));
        pedido.setIdPagamento(Long.valueOf(req.getParameter("pagamento")));
        pedido.setStatus("RECEBIDO");

        PedidoDao pedidoDao = new PedidoDao();

        for (CarrinhoItem ci : carrinho.getItens()) {
            ItemPedido item = new ItemPedido();
            if ("pizza".equals(ci.getTipo())) item.setIdPizza(ci.getId());
            else item.setIdProduto(ci.getId());
            item.setQuantidade(ci.getQuantidade());
            item.setPrecoUnitario(ci.getPreco());     // já veio do banco
            pedido.getItens().add(item);
        }
        pedido.setValor(carrinho.getTotal());    // o carrinho já soma tudo

        try {
            pedidoDao.salvarPedido(pedido);
            carrinho.limpar();                        // só limpa depois de salvar
            resp.sendRedirect("historico.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
            resp.sendError(500, "Erro ao salvar pedido");
        }
    }
}
