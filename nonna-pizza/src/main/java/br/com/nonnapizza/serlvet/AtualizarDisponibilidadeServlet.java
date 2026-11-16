package br.com.nonnapizza.serlvet;

import br.com.nonnapizza.dao.PizzaDao;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet("/atualizar-disponibilidade")
public class AtualizarDisponibilidadeServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        boolean disponivel = req.getParameter("disponivel") != null;   // checkbox marcado envia o parâmetro

        if (id == null || id.isBlank()) {
            resp.sendError(400, "Pizza inválida");
            return;
        }

        try {
            new PizzaDao().atualizarPizzaDisponibilidade(id, disponivel);
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(500, "Erro ao atualizar disponibilidade");
            return;
        }

        resp.sendRedirect("find-all-pizzas");
    }
}
