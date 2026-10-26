package br.com.nonnapizza.serlvet;


import br.com.nonnapizza.dao.PizzaDao;
import br.com.nonnapizza.model.Pizza;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/create-pizza")
public class CreatePizzaServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String idPizza = request.getParameter("idPizza");
        String sabor = request.getParameter("sabor");
        String tipo = request.getParameter("tipo");
        float valor = Float.parseFloat(request.getParameter("valor"));
        String descricao = request.getParameter("descricao");

        Pizza pizza = new Pizza();

        pizza.setSabor(sabor);
        pizza.setTipo(tipo);
        pizza.setValor(valor);
        pizza.setDescricao(descricao);

        PizzaDao pizzaDao = new PizzaDao();

        if (idPizza.isBlank()) {
            pizzaDao.inserirPizza(pizza);
        }
        else {
            pizzaDao.atualizarPizza(pizza);
        }

        response.sendRedirect("/find-all-pizzas");
    }
}
