package br.com.nonnapizza.serlvet;

import br.com.nonnapizza.dao.EnderecoDao;
import br.com.nonnapizza.model.Cliente;
import br.com.nonnapizza.model.Endereco;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cliente cliente = (Cliente) req.getSession().getAttribute("loggeduser");

        if (cliente == null) {
            req.getRequestDispatcher("login.html").forward(req, resp);
            return;
        }

        List<Endereco> enderecos = new EnderecoDao().listarEnderecos(cliente.getId());
        req.setAttribute("enderecos", enderecos);
        req.getRequestDispatcher("checkout.jsp").forward(req, resp);
    }
}
