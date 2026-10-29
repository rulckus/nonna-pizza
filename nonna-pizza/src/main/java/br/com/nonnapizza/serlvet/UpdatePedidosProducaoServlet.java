package br.com.nonnapizza.serlvet;

import br.com.nonnapizza.dao.PedidoDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/atualizar-status")
public class UpdatePedidosProducaoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        PedidoDao pedidoDao = new PedidoDao();
        String status = pedidoDao.verificarStatus(id);


        if (status.equals("RECEBIDO")) {
            pedidoDao.atualizarPedidoPreparando(id);
        }
        if (status.equals("PREPARANDO")) {
            pedidoDao.atualizarPedidoAssando(id);
        }
        if (status.equals("ASSANDO")) {
            pedidoDao.atualizarPedidoPronto(id);
        }
        if (status.equals("PRONTO")) {
            pedidoDao.atualizarPedidoFinalizado(id);
        }

        resp.sendRedirect("/producao");
    }
}
