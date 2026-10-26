package br.com.nonnapizza.serlvet;

import br.com.nonnapizza.dao.PizzaDao;
import br.com.nonnapizza.model.Pizza;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/cardapio")
public class CardapioServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Pizza> pizzas = new PizzaDao().listarPizzasCardapio();

        req.setAttribute("pizzasCardapio", pizzas);

        req.getRequestDispatcher("/cardapio.jsp").forward(req, resp);
    }
}
