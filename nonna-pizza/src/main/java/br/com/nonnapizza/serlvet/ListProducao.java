package br.com.nonnapizza.serlvet;

import br.com.nonnapizza.dao.PedidoDao;
import br.com.nonnapizza.dao.PizzaDao;
import br.com.nonnapizza.model.Pedido;
import br.com.nonnapizza.model.PedidoCard;
import br.com.nonnapizza.model.Pizza;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/producao")
public class ListProducao extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<PedidoCard> pedidosRecebidos = new PedidoDao().listarPedidosProducaoRecebidos();
        List<PedidoCard> pedidosPreparando = new PedidoDao().listarPedidosProducaoPreparando();
        List<PedidoCard> pedidosAssando = new PedidoDao().listarPedidosProducaoAssando();
        List<PedidoCard> pedidosPronto = new PedidoDao().listarPedidosProducaoPronto();

        req.setAttribute("tamanhoRecebidos", pedidosRecebidos.size());
        req.setAttribute("tamanhoPreparando", pedidosPreparando.size());
        req.setAttribute("tamanhoAssando", pedidosAssando.size());
        req.setAttribute("tamanhoPronto", pedidosPronto.size());


        req.setAttribute("pedidosRecebidos", pedidosRecebidos);
        req.setAttribute("pedidosPreparando", pedidosPreparando);
        req.setAttribute("pedidosAssando", pedidosAssando);
        req.setAttribute("pedidosPronto", pedidosPronto);

        req.getRequestDispatcher("producao.jsp").forward(req, resp);
    }
}
