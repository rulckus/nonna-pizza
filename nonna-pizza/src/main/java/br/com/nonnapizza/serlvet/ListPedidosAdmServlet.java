package br.com.nonnapizza.serlvet;

import br.com.nonnapizza.dao.PedidoDao;
import br.com.nonnapizza.dao.ProdutosDao;
import br.com.nonnapizza.model.PedidosAdm;
import br.com.nonnapizza.model.Produtos;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/pedidos-adm")
public class ListPedidosAdmServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<PedidosAdm> pedidos = new PedidoDao().listarPedidosAdm();

        req.setAttribute("pedidos", pedidos);

        req.getRequestDispatcher("pedidos.jsp").forward(req, resp);
    }
}
