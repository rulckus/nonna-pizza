package br.com.nonnapizza.serlvet;

import br.com.nonnapizza.dao.PedidoDao;
import br.com.nonnapizza.dao.PizzaDao;
import br.com.nonnapizza.model.Cliente;
import br.com.nonnapizza.model.Pedido;
import br.com.nonnapizza.model.Pizza;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/historico")
public class ListHistoricoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cliente clienteLogado = (Cliente) req.getSession().getAttribute("loggeduser");

        if (clienteLogado == null) {
            resp.sendRedirect("/login.html");
        }else {
            List<Pedido> pedidos = new PedidoDao().listarPedidos(clienteLogado.getId());

            req.setAttribute("pedidos", pedidos);

            req.getRequestDispatcher("historico.jsp").forward(req, resp);
        }
    }
}
