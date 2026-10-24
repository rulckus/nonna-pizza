package br.com.nonnapizza.serlvet;

import br.com.nonnapizza.dao.PizzaDao;
import br.com.nonnapizza.model.Pizza;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete-pizza")
public class DeletePizzaServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pizzaId = req.getParameter("id");

        PizzaDao pizza = new PizzaDao();

        pizza.deletePizzaById(pizzaId);

        resp.sendRedirect("/find-all-pizzas");
    }
}
